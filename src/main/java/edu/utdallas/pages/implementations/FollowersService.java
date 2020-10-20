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
        query(getQuery("FOLLOW"),follower,following);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unFollowUser(String follower, String following) {
        query(getQuery("UNFOLLOW"),follower,following);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFollowing(String follower, String following) {
        return exists(getQuery("IS_FOLLOWING"),follower,following);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String retrieveFollowers(String member) {
        String[] column = {"follower"};
        return retrieveAsJsonArr("followers",column,column,getQuery("RETRIEVE_FOLLOWERS"),member);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String retrieveFollowing(String member) {
        String[] column = {"following"};
        return retrieveAsJsonArr("following",column,column,getQuery("RETRIEVE_FOLLOWING"), member);
    }

}
