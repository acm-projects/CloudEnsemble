package edu.utdallas.pages.models;

import edu.utdallas.pages.Database;
import edu.utdallas.pages.controllers.TagType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("TagsService")
public class TagsService implements ITagsService {

    private final IDataSource dataSource;

    public TagsService(IDataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newTag(String tagId, TagType type) {
        Database.query(dataSource,dataSource.getQuery("KEY_TAG"),tagId, "0", type.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTag(String clipKey, String tagId) {
        Database.query(dataSource,dataSource.getQuery("ADD_TAG"), clipKey, tagId);
        Database.query(dataSource,dataSource.getQuery("INCREMENT_TAG"), tagId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean clipHasTag(String clipKey, String tagId) {
        return Database.exists(dataSource,dataSource.getQuery("CLIP_HAS_TAG"), clipKey, tagId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeTag(String clipKey, String tagId) {
        Database.query(dataSource,dataSource.getQuery("REMOVE_TAG"), clipKey, tagId);
        Database.query(dataSource,dataSource.getQuery("DECREMENT_TAG"), tagId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAllTags(String clipKey) {
        Database.query(dataSource,dataSource.getQuery("REMOVE_ALL_TAGS"), clipKey);
        Database.query(dataSource,dataSource.getQuery("DECREMENT_ALL_TAGS"), clipKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String retrieveTags(String clipKey) {
        String[] columns = {"tag_id"};
        JSONObject obj = new JSONObject();
        JSONArray genreTags = Database.retrieveAsJsonArrObj(dataSource,columns,
                columns, dataSource.getQuery("RETRIEVE_GENRE_TAGS"), clipKey);
        JSONArray artistTags  = Database.retrieveAsJsonArrObj(dataSource,columns,
                columns, dataSource.getQuery("RETRIEVE_ARTIST_TAGS"), clipKey);
        JSONArray instrumentTags = Database.retrieveAsJsonArrObj(dataSource,columns,
                columns, dataSource.getQuery("RETRIEVE_INSTRUMENT_TAGS"), clipKey);
        JSONArray otherTags  = Database.retrieveAsJsonArrObj(dataSource,columns,
                columns, dataSource.getQuery("RETRIEVE_OTHER_TAGS"), clipKey);
        obj.put("genre_tags",genreTags);
        obj.put("artist_tags",artistTags);
        obj.put("instrument_tags",instrumentTags);
        obj.put("other_tags",otherTags);
        return obj.toString();
    }

}
