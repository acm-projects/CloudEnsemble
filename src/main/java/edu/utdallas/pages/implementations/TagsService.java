package edu.utdallas.pages.implementations;

import edu.utdallas.pages.controllers.TagType;
import edu.utdallas.pages.services.IDataSource;
import edu.utdallas.pages.services.ITagsService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("TagsService")
public class TagsService extends DbService implements ITagsService {

    public TagsService(@Qualifier("DataSource") IDataSource dataSource) {
        super(dataSource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newTag(String tagId, TagType type) {
        Database.query(getDataSource(),getQuery("NEW_TAG"),tagId, "0", type.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTag(String clipKey, String tagId) {
        Database.query(getDataSource(),getQuery("ADD_TAG"), clipKey, tagId);
        Database.query(getDataSource(),getQuery("INCREMENT_TAG"), tagId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean clipHasTag(String clipKey, String tagId) {
        return Database.exists(getDataSource(),getQuery("CLIP_HAS_TAG"), clipKey, tagId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeTag(String clipKey, String tagId) {
        Database.query(getDataSource(),getQuery("REMOVE_TAG"), clipKey, tagId);
        Database.query(getDataSource(),getQuery("DECREMENT_TAG"), tagId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAllTags(String clipKey) {
        Database.query(getDataSource(),getQuery("REMOVE_ALL_TAGS"), clipKey);
        Database.query(getDataSource(),getQuery("DECREMENT_ALL_TAGS"), clipKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String retrieveTags(String clipKey) {
        String[] columns = {"tag_id"};
        JSONObject obj = new JSONObject();
        JSONArray genreTags = Database.retrieveAsJsonArrObj(getDataSource(),columns,
                columns, getQuery("RETRIEVE_GENRE_TAGS"), clipKey);
        JSONArray artistTags  = Database.retrieveAsJsonArrObj(getDataSource(),columns,
                columns, getQuery("RETRIEVE_ARTIST_TAGS"), clipKey);
        JSONArray instrumentTags = Database.retrieveAsJsonArrObj(getDataSource(),columns,
                columns, getQuery("RETRIEVE_INSTRUMENT_TAGS"), clipKey);
        JSONArray otherTags  = Database.retrieveAsJsonArrObj(getDataSource(),columns,
                columns, getQuery("RETRIEVE_OTHER_TAGS"), clipKey);
        obj.put("genre_tags",genreTags);
        obj.put("artist_tags",artistTags);
        obj.put("instrument_tags",instrumentTags);
        obj.put("other_tags",otherTags);
        return obj.toString();
    }

}
