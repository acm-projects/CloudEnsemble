package edu.utdallas.pages.services;

import java.sql.SQLException;

public interface ICredentialsService {

    /**
     * Registers a user into the database
     * @param email email for registration
     * @param username the username for registration
     * @param hashedPassword hashed password
     * @param salt of the password
     */
    void register(String email, String username, String hashedPassword, String salt) throws SQLException;

    /**
     * Validates a login input is valid, returns unique username if login is successful
     * returns an empty string otherwise
     * @param email email for login
     * @param password password for login
     * @return user name login, empty string if login fails
     */
    String login(String email, String password);

    /**
     * Resets a password
     * @param email for account for reset
     * @param newPassword new password
     */
    void resetPassword(String email, String newPassword);

    /**
     * Gets the salt for a user
     * @param email email of user
     * @return salt
     */
    String getSalt(String email);

    /**
     * Checks if the email is already in the database for registration or if it is a valid address
     * @param email to check
     * @return false if email exists or is invalid
     */
    boolean checkEmail(String email);

    /**
     * Checks if the email is already in the database
     * @param email to check
     * @return false if email exists or is invalid
     */
    boolean emailExists(String email);

    /**
     * Checks if the name is already in the database for registration
     * @param name to check
     * @return true if name doesn't exist, false otherwise
     */
    boolean checkName(String name);

    /**
     * Checks if the name is already in the database for registration
     * @param  password to check
     * @return true if it matches the password policy
     */
    boolean checkPassword(String password);

}
