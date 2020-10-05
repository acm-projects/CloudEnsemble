package edu.utdallas.pages.models;

public interface ICredentialsService {

    /**
     * Registers a user into the database
     * @param email email for registration
     * @param username the username for registration
     * @param password password for registration
     */
    void register(String email, String username, String password);

    /**
     * Validates a login input is valid, returns unique username if login is successful
     * returns an empty string otherwise
     * @param email email for login
     * @param password password for login
     * @return user name login, empty string if login fails
     */
    String login(String email, String password);

    /**
     * Gets the salt for a user
     * @param email email of user
     * @return salt
     */
    String getSalt(String email);

    /**
     * Checks if the email is already in the database for registration
     * @param email to check
     * @return true if email doesn't exist, false otherwise
     */
    boolean checkEmail(String email);

    /**
     * Checks if the name is already in the database for registration
     * @param name to check
     * @return true if name doesn't exist, false otherwise
     */
    boolean checkName(String name);

}
