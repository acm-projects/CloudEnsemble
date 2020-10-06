package edu.utdallas.pages.implementations;

import edu.utdallas.pages.services.IClipsService;
import edu.utdallas.pages.services.IDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("ClipsService")
public class ClipsService extends DbService implements IClipsService {

    public ClipsService(@Qualifier("DataSource") IDataSource dataSource) {
        super(dataSource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String retrieveClips(String user) {
        String[] column = {"clip_key","clip_name"};
        return Database.retrieveAsJsonArr(getDataSource(),column,column,getQuery("RETRIEVE_USER_CLIPS"),user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean clipExists(String user, String name) {
        return Database.exists(getDataSource(),getQuery("RETRIEVE_CLIP"),user,name);
    }

}
