package edu.utdallas.pages.controllers;

import com.fasterxml.uuid.Generators;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import edu.utdallas.pages.services.ICredentialsService;
import edu.utdallas.pages.services.IHashService;
import edu.utdallas.pages.services.IProfileService;
import edu.utdallas.pages.services.ISEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class UserController extends HttpController {

    public static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public static final String RESET_PAGE_PATH = "/reset-password";

    @Autowired
    private ServletContext servletContext;
    private final SecureRandom rnd = new SecureRandom();

    private final ICredentialsService credentialsService;
    private final ISEService emailService;
    private final IHashService hashService;
    private final IProfileService profileService;

    //<Email,Account>
    private final LoadingCache<String, Account> accountMap = CacheBuilder.newBuilder()
            .maximumSize(10000)
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .build(new CacheLoader<>() {
                @Override
                public Account load(String key) {
                    return null;
                }
            });

    //<Code,Email>
    private final LoadingCache<String, String> resetMap = CacheBuilder.newBuilder()
            .maximumSize(10000)
            .expireAfterWrite(15, TimeUnit.MINUTES)
            .build(new CacheLoader<>() {
                @Override
                public String load(String key) {
                    return null;
                }
            });

    @Autowired
    public UserController(@Qualifier("CredentialsService") ICredentialsService credService,
                          @Qualifier("SEService") ISEService emailService,
                          @Qualifier("HashService") IHashService hashService,
                          @Qualifier("ProfileService") IProfileService profilesService) {
        this.credentialsService = credService;
        this.emailService = emailService;
        this.hashService = hashService;
        this.profileService = profilesService;
    }

    /**
     * Gets the username that is currently logged in
     * @param request request
     * @return the user that is logged in, returns empty string if not logged in
     */
    @ResponseBody
    @RequestMapping(value = "/logged-in", method = RequestMethod.GET, produces = "application/json")
    public String getLoggedInAs(HttpServletRequest request) {
        String user = getStringAttribute(request.getSession(), UserController.USERNAME_ATTRIBUTE);
        String[] pair = {"user",user};
        return JsonUtils.createJson(pair);
    }

    /**
     * Handles a login post request
     * @param request request
     * @param email the email to login
     * @param password the password to login
     * @return json that identifies success with a status key
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public String handleLogin(HttpServletRequest request,
                              @RequestParam(value="email") String email,
                              @RequestParam(value="password") String password) {
        HttpSession session = request.getSession();
        String login = credentialsService.login(email, password);
        if (!login.equals("")) {
            session.setAttribute(USERNAME_ATTRIBUTE,login);
            return Status.SUCCESS.getJson();
        }
        return Status.FAIL.getJson();
    }

    /**
     * Handles a register post request
     * @param email the email to register
     * @param userName the name to register
     * @param password the plaintext password to register
     * @return a json determining success status of email and of username
     */
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public String handleRegister(@RequestParam(value="email") String email,
                                 @RequestParam(value="user_name") String userName,
                                 @RequestParam(value="password") String password) {
        boolean checkEmail = credentialsService.checkEmail(email);
        boolean checkName = credentialsService.checkName(userName);
        boolean checkPassword = credentialsService.checkPassword(password);
        if(checkEmail && checkName && checkPassword) {
            String code = generateCode();
            String salt = hashService.generateSalt();
            String hashedPassword = hashService.hashString(password,salt);
            accountMap.put(email, new Account(code,userName,hashedPassword,salt));
            emailService.sendVerificationEmail(email,code);
        }
        String emailStatus = checkEmail ? "success" : "fail";
        String nameStatus = checkName ? "success" : "fail";
        String passwordStatus = checkPassword ? "success" : "fail";
        String[][] pairs = {{"email_status",emailStatus},{"username_status",nameStatus},{"password_status",passwordStatus}};
        return JsonUtils.createJson(pairs);
    }

    /**
     * Verify an account waiting to be registered
     * @param email for account
     * @param code to register account
     * @return fail if code is incorrect, doesn't exist, or if execution fails
     */
    @ResponseBody
    @RequestMapping(value = "/account/verify", method = RequestMethod.POST, produces = "application/json")
    public String verifyAccount(@RequestParam(value="email") String email,
                                @RequestParam(value="code") String code) {
        Account acc;
        try {
            acc = accountMap.get(email);
        } catch (ExecutionException | CacheLoader.InvalidCacheLoadException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, null, e);
            return Status.FAIL.getJson();
        }
        if(!acc.getCode().equals(code)) {
            return Status.FAIL.getJson();
        }
        try {
            credentialsService.register(email,acc.getUser(),acc.getHashedPassword(),acc.getSalt());
            emailService.sendVerificationSuccessEmail(email,acc.getUser());
            return Status.SUCCESS.getJson();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return Status.FAIL.getJson();
        }
    }

    /**
     * Sends a password request to a valid email
     * @param email to send request
     * @return success or fail
     */
    @ResponseBody
    @RequestMapping(value = "/account/reset-password", method = RequestMethod.POST, produces = "application/json")
    public String sendPasswordResetRequest(@RequestParam(value="email") String email) {
        if(credentialsService.emailExists(email)) {
            String code = generateLongCode();
            String pathContext = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
            String url = pathContext + RESET_PAGE_PATH + "?token=" + code;
            resetMap.put(code,email);
            emailService.sendPasswordResetEmail(email,url);
            return Status.SUCCESS.getJson();
        }
        return Status.FAIL.getJson();
    }

    /**
     * Sends the user to a secure password reset page
     * @param code on page
     * @return secure page
     */
    @RequestMapping(value = RESET_PAGE_PATH, method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String resetPasswordPage(ModelMap model, @RequestParam(value="token") String code) {
        String email;
        try {
            email = resetMap.get(code);
        } catch (ExecutionException | CacheLoader.InvalidCacheLoadException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, null, e);
            System.out.println("Error");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
        if(email == null || email.equals("")) {
            System.out.println("Error");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
        model.put("code",code);
        return "reset-password.jsp";
    }

    /**
     * Resets password with secure token
     * @param code token
     * @param password to change to
     * @return page showing success or failure
     */
    @RequestMapping(value = "/verify-reset", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public String resetPassword(ModelMap model,
                                @RequestParam(value="token") String code,
                                @RequestParam(value="new_password") String password) {
        if(!credentialsService.checkPassword(password)) {
            model.put("title","Reset Failed");
            model.put("message","Password doesn't meet requirements.");
            return "message.jsp";
        }
        String email;
        try {
            email = resetMap.get(code);
        } catch (ExecutionException | CacheLoader.InvalidCacheLoadException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, null, e);
            model.put("title","Reset Failed");
            model.put("message","An error occurred when trying to reset your password.");
            return "message.jsp";
        }
        if(email == null || email.equals("")) {
            model.put("title","Reset Failed");
            model.put("message","15 minute time limited expired.");
            return "message.jsp";
        }
        credentialsService.resetPassword(email,password);
        emailService.sendResetSuccessEmail(email);
        model.put("title","Reset Successful");
        model.put("message","Password reset successfully!");
        return "message.jsp";
    }

    /**
     * Change the description of a user's account
     * @param request http request
     * @param userName to change for
     * @param desc to change to
     * @return json with success or failure
     */
    @ResponseBody
    @RequestMapping(value = "/account/desc/change", method = RequestMethod.POST, produces = "application/json")
    public String changeDesc(HttpServletRequest request,
                             @RequestParam(value="user_name") String userName,
                             @RequestParam(value="desc") String desc) {
        String loggedIn = getStringAttribute(request.getSession(), USERNAME_ATTRIBUTE);
        if(loggedIn == null) {
            return Status.DENIED.getJson();
        }
        if(loggedIn.equals(userName)) {
            profileService.changeDescription(userName,desc);
            return Status.SUCCESS.getJson();
        }
        return Status.DENIED.getJson();
    }

    /**
     * Change the description of a user's account
     * @param userName to get for
     * @return description in a json (with one element)
     */
    @ResponseBody
    @RequestMapping(value = "/account/{userName}/desc", method = RequestMethod.GET, produces = "application/json")
    public String retrieveDescription(@PathVariable String userName) {
        return profileService.retrieveDescription(userName);
    }

    /**
     * Generates a 6 digit code
     * @return the 6 digit code
     */
    public String generateCode() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    /**
     * Generates a 98 character alphanumeric code
     * @return the long code
     */
    public String generateLongCode() {
        StringBuilder sb = new StringBuilder(60+38);
        sb.append(getUUID()); //38 characters
        for (int i = 0; i < 60; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    /**
     * UUID to guarantee time uniqueness
     * @return uuid
     */
    public String getUUID() {
        return Generators.timeBasedGenerator().generate().toString() + Thread.currentThread().getId();
    }

}
