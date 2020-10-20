package edu.utdallas.pages.implementations;

import edu.utdallas.pages.services.TagType;
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
    public void addTag(String clipKey, String tagId, TagType type) {
        query(getQuery("ADD_TAG"), clipKey, tagId, type.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean objectHasTag(String key, String tagId) {
        return exists(getQuery("CLIP_HAS_TAG"), key, tagId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeTag(String clipKey, String tagId) {
        query(getQuery("REMOVE_TAG"), clipKey, tagId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAllTags(String clipKey) {
        query(getQuery("REMOVE_ALL_TAGS"), clipKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String retrieveTags(String key) {
        String[] columns = {"tag_id"};
        JSONObject obj = new JSONObject();
        JSONArray genreTags = retrieveAsJsonArrObj(columns,
                columns, getQuery("RETRIEVE_GENRE_TAGS"), key);
        JSONArray artistTags  = retrieveAsJsonArrObj(columns,
                columns, getQuery("RETRIEVE_ARTIST_TAGS"), key);
        JSONArray instrumentTags = retrieveAsJsonArrObj(columns,
                columns, getQuery("RETRIEVE_INSTRUMENT_TAGS"), key);
        JSONArray otherTags  = retrieveAsJsonArrObj(columns,
                columns, getQuery("RETRIEVE_OTHER_TAGS"), key);
        obj.put("genre_tags",genreTags);
        obj.put("artist_tags",artistTags);
        obj.put("instrument_tags",instrumentTags);
        obj.put("other_tags",otherTags);
        return obj.toString();
    }

}
