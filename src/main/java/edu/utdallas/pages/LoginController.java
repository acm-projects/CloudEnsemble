package edu.utdallas.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    public static final String USERNAME_ATTRIBUTE = "user_name";
    public static final String EMAIL_PARAM = "email";
    public static final String PASSWORD_PARAM = "password";

    /**
     * Handles a login post request
     * @param request request
     * @return json that identifies success with a status key
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String handleLogin(HttpServletRequest request) {
        String[] success = {"status","success"};
        String[] fail = {"status","failed"};
        String email = request.getParameter(EMAIL_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        HttpSession session = request.getSession();
        String login = DbUtils.login(email, password);
        if (!login.equals("")) {
            session.setAttribute(USERNAME_ATTRIBUTE,login);
            return JsonUtils.createJson(success);
        }
        return JsonUtils.createJson(fail);
    }

}
