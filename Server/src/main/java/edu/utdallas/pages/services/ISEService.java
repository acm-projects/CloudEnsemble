package edu.utdallas.pages.services;

public interface ISEService {

    /**
     * Send a basic message to an address
     * @param to target email address
     * @param subject of email
     * @param message to send
     */
    void sendEmail(String to, String subject, String message);

    /**
     * Send a request to reset a password to an email
     * @param to target email address
     * @param link link to safety reset the password
     */
    void sendPasswordResetEmail(String to, String link);

    /**
     * Send a verification email with a code to a target
     * @param to target email address
     * @param code to send
     */
    void sendVerificationEmail(String to, String code);

    /**
     * Send a verification success confirmation email to a target
     * @param to target email address
     * @param user in message
     */
    void sendVerificationSuccessEmail(String to, String user);

    /**
     * Send a password reset success confirmation email to a target
     * @param to target email address
     */
    void sendResetSuccessEmail(String to);

}
