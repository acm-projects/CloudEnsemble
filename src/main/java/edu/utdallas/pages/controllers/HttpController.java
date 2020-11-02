package edu.utdallas.pages.controllers;

import javax.servlet.http.HttpSession;

public class HttpController {

    public static final String USERNAME_ATTRIBUTE = "user_name";

    /**
     * Get a string attribute from session
     * @param session session to get from
     * @param attribute name of attribute
     * @return string stored at attribute
     */
    public String getStringAttribute(HttpSession session, String attribute) {
        return (String)session.getAttribute(attribute);
    }

    public boolean isInputInValid(String input) {
        int len = input.length();
        return len < 5 || len > 40;
    }

}
