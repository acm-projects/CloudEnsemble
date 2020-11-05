package edu.utdallas.pages.services;

public interface IStarsService {

    /**
     * Retrieve all stars for a user
     * @param userName of user
     * @return as json arrow
     */
    String retrieveUserStars(String userName);

    /**
     * Add a new star to an entity
     * @param userName userName starring
     * @param objectKey to be starred
     */
    void addStar(String userName, String objectKey);

    /**
     * Remove a star from an entity
     * @param userName userName un-starring
     * @param objectKey to be un-starred
     */
    void removeStar(String userName, String objectKey);

    /**
     * Checks if a user has already starred an object
     * @param userName user
     * @param objectKey object
     * @return true if user has starred object false otherwise
     */
    boolean hasStarred(String userName, String objectKey);
}
