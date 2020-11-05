package edu.utdallas.pages.implementations;

import edu.utdallas.pages.services.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("AccessService")
public class AccessService extends DbService implements IAccessService {

    public AccessService(@Qualifier("DataSource") IDataSource dataSource) {
        super(dataSource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canModifyClip(String clipKey, String userName) {
        return isClipOwner(clipKey, userName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canViewTrack(String trackKey, String userName) {
        String access = retrieve("access",getQuery("GET_TRACK_ACCESS"), trackKey);
        return retrieve("track_uploader",getQuery("GET_TRACK_UPLOADER"),trackKey).equals(userName) ||
                !access.equals(AccessLevel.RESTRICTED.getId()) ||
                exists(getQuery("CAN_USER_VIEW"),trackKey,userName,trackKey,userName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canDeleteTrack(String key, String userName) {
        return isTrackOwner(key, userName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canEditTrack(String trackKey, String userName) {
        return retrieve("track_uploader",getQuery("GET_TRACK_UPLOADER"),trackKey).equals(userName) ||
                exists(getQuery("CAN_USER_EDIT"),trackKey,userName,trackKey,userName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAccessTrack(String key, String level) {
        query(getQuery("SET_ACCESS_TRACK"), level, key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAccessor(String key, String type, String accessorId, String accessorType) {
        query(getQuery("ADD_ACCESSOR"), key, type, accessorId, accessorType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAccessor(String key, String accessor) {
        query(getQuery("REMOVE_ACCESSOR"), key, accessor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearAccessors(String key) {
        query(getQuery("REMOVE_ALL_ACCESSORS"), key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isClipOwner(String clipKey, String userName) {
        String column = "clip_uploader";
        return retrieve(column,getQuery("GET_CLIP_UPLOADER"),clipKey).equals(userName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTrackOwner(String clipKey, String userName) {
        String column = "track_uploader";
        return retrieve(column,getQuery("GET_TRACK_UPLOADER"),clipKey).equals(userName);
    }
}
