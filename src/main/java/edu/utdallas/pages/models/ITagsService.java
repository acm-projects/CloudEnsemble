package edu.utdallas.pages.models;

import edu.utdallas.pages.controllers.TagType;

public interface ITagsService {

    /**
     * Adds a new UNIQUE tag to the list of all tags
     * @param tagId id of tag
     * @param type Artist, Genre, Instrument, Other
     */
    void newTag(String tagId, TagType type);

    /**
     * Adds a new tag to a clip
     * @param clipKey key to identify clip
     * @param tagId id of tag to add
     */
    void addTag(String clipKey, String tagId);

    /**
     * Returns if a clip has a tag already
     * @param clipKey key to identify clip
     * @param tagId id of tag to add
     */
    boolean clipHasTag(String clipKey, String tagId);

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
