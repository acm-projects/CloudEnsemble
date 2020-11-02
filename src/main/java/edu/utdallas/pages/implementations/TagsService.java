package edu.utdallas.pages.implementations;

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
    public void addTag(String clipKey, String tagId) {
        query(getQuery("ADD_TAG"), clipKey, tagId);
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
        JSONArray tags = retrieveAsJsonArrObj(columns,
                columns, getQuery("RETRIEVE_ALL_TAGS"), key);
        obj.put("tags",tags);
        return obj.toString();
    }

}
