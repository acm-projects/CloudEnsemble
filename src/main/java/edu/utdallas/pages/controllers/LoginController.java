package edu.utdallas.pages.controllers;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import edu.utdallas.pages.services.ICredentialsService;
import edu.utdallas.pages.services.IHashService;
import edu.utdallas.pages.services.ISEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class LoginController {

    public static final String USERNAME_ATTRIBUTE = "user_name";

    private final ICredentialsService credentialsService;
    private final ISEService emailService;
    private final IHashService hashService;

    private final LoadingCache<String, Account> accountMap = CacheBuilder.newBuilder()
            .maximumSize(10000)
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .build(new CacheLoader<>() {
                @Override
                public Account load(String key) throws Exception {
                    return null;
                }
            });

    @Autowired
    public LoginController(@Qualifier("CredentialsService") ICredentialsService credService,
                           @Qualifier("SEService") ISEService emailService,
                           @Qualifier("HashService") IHashService hashService) {
        this.credentialsService = credService;
        this.emailService = emailService;
        this.hashService = hashService;
    }

    /**
     * Handles a login post request
     * @param request request
     * @param email the email to login
     * @param password the password to login
     * @return json that identifies success with a status key
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
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
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String handleRegister(@RequestParam(value="email") String email,
                                 @RequestParam(value="user_name") String userName,
                                 @RequestParam(value="password") String password) {
        boolean checkEmail = credentialsService.checkEmail(email);
        boolean checkName = credentialsService.checkName(userName);
        boolean checkPassword = credentialsService.checkPassword(password);
        if(checkEmail && checkName && checkPassword) {
            String code = generateCode();
            String salt = hashService.generateSalt();
            accountMap.put(email, new Account(code,userName,hashService.hashString(password,salt),salt));
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
    @RequestMapping(value = "/account/verify", method = RequestMethod.POST)
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
            emailService.sendSuccessEmail(email,acc.getUser());
            return Status.SUCCESS.getJson();
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return Status.FAIL.getJson();
        }
    }

    /**
     * Generates a 6 digit code not in the hash map
     * @return the 6 digit code
     */
    public String generateCode() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

}
