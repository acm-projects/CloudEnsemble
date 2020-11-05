package edu.utdallas.pages.controllers;

public class Account {

    private final String code;
    private final String user;
    private final String hashedPassword;
    private final String salt;

    public Account(String code, String user, String hashedPassword, String salt) {
        this.code = code;
        this.user = user;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
    }

    public String getCode() {
        return code;
    }

    public String getUser() {
        return user;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getSalt(){
        return salt;
    }
}
