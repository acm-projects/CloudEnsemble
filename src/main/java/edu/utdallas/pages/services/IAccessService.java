package edu.utdallas.pages.services;

public interface IAccessService {

    /**
     * Checks accessor has permissions to view a clip
     * @param clipKey key of object
     * @param userName to check access
     * @return can access
     */
    boolean canViewClip(String clipKey, String userName);

    /**
     * Checks accessor has permissions to delete a clip
     * @param clipKey key of object
     * @param userName to check access
     * @return can access
     */
    boolean canDeleteClip(String clipKey, String userName);

    /**
     * Checks accessor has permissions to view a clip
     * @param trackKey key of track
     * @param userName to check access
     * @return can access
     */
    boolean canViewTrack(String trackKey, String userName);


    /**
     * Checks accessor has permissions to delete a clip
     * @param clipKey key of object
     * @param userName to check access
     * @return can access
     */
    boolean canDeleteTrack(String clipKey, String userName);

    /**
     * Checks accessor has permissions to edit a clip
     * @param clipKey key of object
     * @param userName to check access
     * @return can access
     */
    boolean canEditTrack(String clipKey, String userName);

    /**
     * Set the access level
     * @param key set access for a specific key
     * @param level level of accessibility
     */
    void setAccess(String key, AccessLevel level);

    /**
     * Add another accessor for an object
     * @param key key for object
     * @param accessType type of access (read/write)
     * @param accessorId accessor that is allowed
     * @param accessorType type of accessor (band/user)
     */
    void addAccess(String key, AccessType accessType, String accessorId, AccessorType accessorType);

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

}
