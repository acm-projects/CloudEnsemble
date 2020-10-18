package edu.utdallas.pages.services;

public interface ISEService {

    /**
     * Send a basic message to an address
     * @param to target email address
     * @param message to send
     */
    void sendEmail(String to, String message);

    /**
     * Send a verification email with a code to a target
     * @param to target email address
     * @param code to send
     */
    void sendVerificationEmail(String to, String code);

    /**
     * Send a success confirmation email to a target
     * @param to target email address
     * @param user in message
     */
    void sendSuccessEmail(String to, String user);

}
