package edu.utdallas.pages.services;

public interface ITagsService {

    /**
     * Adds a new tag to a clip
     * @param clipKey key to identify clip
     * @param tagId id of tag to add
     */
    void addTag(String clipKey, String tagId);

    /**
     * Returns if a clip has a tag already
     * @param key key to identify object
     * @param tagId id of tag to add
     */
    boolean objectHasTag(String key, String tagId);

    /**
     * Remove a tag from a clip
     * @param clipKey key to identify clip
     * @param tagId id of tag to remove
     */
    void removeTag(String clipKey, String tagId);

    /**
     * Remove all tags from a clip
     * @param clipKey key to identify clip
     */
    void removeAllTags(String clipKey);

    /**
     * Retrieve all tags
     * @param clipKey tags for a clip
     * @return retrieve in a json arr
     */
    String retrieveTags(String clipKey);

}
