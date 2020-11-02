package edu.utdallas.pages.services;

public interface IAccessService {

    /**
     * Checks accessor has permissions to modify a clip
     * @param clipKey key of object
     * @param userName to check access
     * @return can access
     */
    boolean canModifyClip(String clipKey, String userName);

    /**
     * Checks accessor has permissions to view a clip
     * @param trackKey key of track
     * @param userName to check access
     * @return can access
     */
    boolean canViewTrack(String trackKey, String userName);


    /**
     * Checks accessor has permissions to delete a clip
     * @param key key of object
     * @param userName to check access
     * @return can access
     */
    boolean canDeleteTrack(String key, String userName);

    /**
     * Checks accessor has permissions to edit a clip
     * @param key key of object
     * @param userName to check access
     * @return can access
     */
    boolean canEditTrack(String key, String userName);

    /**
     * Set the access level to track
     * @param key set access for a specific key
     * @param level level of accessibility (0 - restricted, 1 - view, 2 - edit)
     */
    void setAccessTrack(String key, String level);

    /**
     * Add another accessor for an object
     * @param key key for object
     * @param level of access (1 - view, 2 - edit)
     * @param accessorId accessor that is allowed
     * @param accessorType type of accessor (0 - user, 1 - band)
     */
    void addAccessor(String key, String level, String accessorId, String accessorType);

    /**
     * Removes an accessor from an object
     * @param key of object
     * @param accessor to be removed
     */
    void removeAccessor(String key, String accessor);

    /**
     * Clears all accessors from an object
     * @param key of object
     */
    void clearAccessors(String key);

    /**
     * Checks if a clip is owned by user
     * @param clipKey key of object
     * @param userName to check access
     * @return true or false
     */
    boolean isClipOwner(String clipKey, String userName);

    /**
     * Checks if a clip is owned by user
     * @param clipKey key of object
     * @param userName to check access
     * @return true or false
     */
    boolean isTrackOwner(String clipKey, String userName);

}
