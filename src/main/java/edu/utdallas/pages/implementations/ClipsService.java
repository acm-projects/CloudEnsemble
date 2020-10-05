package edu.utdallas.pages.implementations;

import edu.utdallas.pages.services.IClipsService;
import edu.utdallas.pages.services.IDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("ClipsService")
public class ClipsService implements IClipsService {

    private final IDataSource dataSource;

    public ClipsService(@Qualifier("DataSource") IDataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String retrieveClips(String user) {
        String[] column = {"clip_key","clip_name"};
        return Database.retrieveAsJsonArr(dataSource,column,column,dataSource.getQuery("RETRIEVE_USER_CLIPS"),user);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean clipExists(String user, String name) {
        return Database.exists(dataSource,dataSource.getQuery("RETRIEVE_CLIP"),user,name);
    }

}
