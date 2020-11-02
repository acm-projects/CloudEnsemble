package edu.utdallas.pages.controllers;

public class SystemProperty {

    public static String getenv(String key) {
        String env = System.getenv(key);
        if(env == null) {
            return System.getProperty(key);
        }
        return env;
    }

}
