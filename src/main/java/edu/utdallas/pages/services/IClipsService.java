package edu.utdallas.pages.services;

public interface IClipsService {

    /**
     * Gets all of a user's clips
     * @param user to get for
     * @return clips in a json arr
     */
    String retrieveClips(String user);

    /**
     * Checks if a clip name is already taken
     * @param user the owner of the clip
     * @param name the name of the clip
     * @return true if it already exists
     */
    boolean clipExists(String user, String name);
}
