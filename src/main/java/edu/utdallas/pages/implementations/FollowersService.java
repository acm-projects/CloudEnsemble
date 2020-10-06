package edu.utdallas.pages.implementations;

import edu.utdallas.pages.services.IDataSource;
import edu.utdallas.pages.services.IFollowersService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("FollowersService")
public class FollowersService extends DbService implements IFollowersService {

    public FollowersService(@Qualifier("DataSource") IDataSource dataSource) {
        super(dataSource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void followUser(String follower, String following) {
        Database.query(getDataSource(),getQuery("FOLLOW"),follower,following);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unFollowUser(String follower, String following) {
        Database.query(getDataSource(),getQuery("UNFOLLOW"),follower,following);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFollowing(String follower, String following) {
        return Database.exists(getDataSource(),getQuery("IS_FOLLOWING"),follower,following);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String retrieveFollowers(String member) {
        String[] column = {"follower"};
        return Database.retrieveAsJsonArr(getDataSource(),column,column,getQuery("RETRIEVE_FOLLOWERS"),member);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String retrieveFollowing(String member) {
        String[] column = {"following"};
        return Database.retrieveAsJsonArr(getDataSource(),column,column,getQuery("RETRIEVE_FOLLOWING"), member);
    }

}
