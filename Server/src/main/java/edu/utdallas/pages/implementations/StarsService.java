package edu.utdallas.pages.implementations;

import edu.utdallas.pages.services.IDataSource;
import edu.utdallas.pages.services.IStarsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("StarsService")
public class StarsService extends DbService implements IStarsService {

    public StarsService(@Qualifier("DataSource") IDataSource dataSource) {
        super(dataSource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String retrieveUserStars(String userName) {
        String[] column = {"object_starred"};
        return retrieveAsJsonArr("stars",column,column,getQuery("GET_STARS"),userName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStar(String userName, String objectKey) {
        query(getQuery("ADD_STAR"),userName,objectKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeStar(String userName, String objectKey) {
        query(getQuery("REMOVE_STAR"),userName,objectKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasStarred(String userName, String objectKey) {
        return exists(getQuery("HAS_STARRED"),userName,objectKey);
    }
}
