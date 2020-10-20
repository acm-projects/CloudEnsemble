package edu.utdallas.pages.implementations;

import edu.utdallas.pages.services.*;
import org.springframework.beans.factory.annotation.Qualifier;

public class AccessService extends DbService implements IAccessService {

    public AccessService(@Qualifier("DataSource") IDataSource dataSource) {
        super(dataSource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canViewClip(String clipKey, String userName) {
        String column = "access";
        String access = retrieve(column,getQuery("GET_CLIP_ACCESS"), clipKey);
        return !access.equals(AccessLevel.CUSTOM.getId()) && !access.equals(AccessLevel.ONLY_EDIT.getId()) ||
                exists(getQuery("CAN_USER_VIEW"),clipKey,userName) || exists(getQuery("CAN_USER_BANDS_VIEW"),clipKey,userName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canDeleteClip(String clipKey, String userName) {
        String column = "clip_uploader";
        return retrieve(column,getQuery("GET_CLIP_UPLOADER"),clipKey).equals("userName");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canViewTrack(String trackKey, String userName) {
        String column = "access";
        String access = retrieve(column,getQuery("GET_TRACK_ACCESS"), trackKey);
        return !access.equals(AccessLevel.CUSTOM.getId()) && !access.equals(AccessLevel.ONLY_EDIT.getId()) ||
                exists(getQuery("CAN_USER_VIEW"),trackKey,userName) || exists(getQuery("CAN_USER_BANDS_VIEW"),trackKey,userName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canDeleteTrack(String clipKey, String userName) {
        String column = "clip_uploader";
        return retrieve(column,getQuery("GET_TRACK_UPLOADER"),clipKey).equals("userName");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canEditTrack(String trackKey, String userName) {
        String column = "access";
        String access = retrieve(column,getQuery("GET_TRACK_ACCESS"), trackKey);
        return access.equals(AccessLevel.ANYONE_EDIT_SEARCHABLE.getId()) && access.equals(AccessLevel.ANYONE_EDIT.getId()) ||
                exists(getQuery("CAN_USER_EDIT"),trackKey,userName) || exists(getQuery("CAN_USER_BANDS_EDIT"),trackKey,userName);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void setAccess(String key, AccessLevel level) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAccess(String key, AccessType accessType, String accessorId, AccessorType accessorType) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAccessor(String key, String accessor) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearAccessors(String key) {

    }
}
