package edu.utdallas.pages.services;

public interface IProfileService {

    /**
     * Changes the description for a user
     * @param username key
     * @param desc to change
     */
    void changeDescription(String username, String desc);

    /**
     * Gets a user's description
     * @param username key
     * @return as JSON
     */
    String retrieveDescription(String username);

}
