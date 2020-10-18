package edu.utdallas.pages.controllers;

import javax.servlet.http.HttpSession;

public class SpringUtils {

    /**
     * Get a string attribute from session
     * @param session session to get from
     * @param attribute name of attribute
     * @return string stored at attribute
     */
    public static String getStringAttribute(HttpSession session, String attribute) {
        return (String)session.getAttribute(attribute);
    }

}
