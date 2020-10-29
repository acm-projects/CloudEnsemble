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
    public void newBand(String founder, String bandName) throws SQLException {
        queryUncaught(getQuery("NEW_BAND"), getUUID(), bandName, getTime(), founder);
    }

    /**
     * {@inheritDoc}
     */
    public void joinBand(String userName, String bandId) {
        query(getQuery("JOIN_BAND"), bandId, userName);
    }

    /**
     * {@inheritDoc}
     */
    public void leaveBand(String userName, String bandId) {
        query(getQuery("LEAVE_BAND"), bandId, userName);
    }

    /**
     * {@inheritDoc}
     */
    public boolean inBand(String userName, String bandId) {
        return exists(getQuery("IN_BAND"), bandId, userName);
    }

}
