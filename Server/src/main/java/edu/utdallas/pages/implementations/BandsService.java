package edu.utdallas.pages.implementations;

import edu.utdallas.pages.services.IBandsService;
import edu.utdallas.pages.services.IDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service("BandsService")
public class BandsService extends DbService implements IBandsService {

    public BandsService(@Qualifier("DataSource") IDataSource dataSource) {
        super(dataSource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newBand(String founder, String bandName) throws SQLException {
        queryUncaught(getQuery("NEW_BAND"), getUUID(), bandName, getTime(), founder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void joinBand(String userName, String bandId) {
        query(getQuery("JOIN_BAND"), bandId, userName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void leaveBand(String userName, String bandId) {
        query(getQuery("LEAVE_BAND"), bandId, userName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean inBand(String userName, String bandId) {
        return exists(getQuery("IN_BAND"), bandId, userName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFounder(String userName, String bandId) {
        return exists(getQuery("IS_FOUNDER"),bandId,userName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteBand(String bandId) {
        query(getQuery("DELETE_BAND"),bandId,bandId,bandId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void kickMember(String bandId, String userName) {
        query(getQuery("KICK_MEMBER"),bandId,userName);
    }

}
