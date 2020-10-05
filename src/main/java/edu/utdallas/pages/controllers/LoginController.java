package edu.utdallas.pages.controllers;

import edu.utdallas.pages.models.CredentialsService;
import edu.utdallas.pages.models.ICredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    public static final String USERNAME_ATTRIBUTE = "user_name";

    private final ICredentialsService credentialsService;

    @Autowired
    public LoginController(@Qualifier("CredentialsService") CredentialsService bandsService) {
        this.credentialsService = bandsService;
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
     * Checks if an email or a username is taken, and sends a verification for that email if it is valid
     * @param email the email to check
     * @param userName the user name to check
     * @return json that identifies if email and username can be used and if email was sent properly
     */
    @ResponseBody
    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public String verifyEmail(@RequestParam(value="user_name") String userName, @RequestParam(value="email") String email) {
        if(credentialsService.checkEmail(email) && (credentialsService.checkName(userName))) {
            return EmailController.sendVerificationEmail(userName, email);
        }
        return Status.TAKEN.getJson();
    }

}
