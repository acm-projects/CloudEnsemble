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
    public void newBand(String bandName) throws SQLException {
        queryUncaught(getQuery("NEW_BAND"), bandName);
    }

    /**
     * {@inheritDoc}
     */
    public void joinBand(String userName, String bandName) {
        query(getQuery("JOIN_BAND"), bandName);
    }

    /**
     * {@inheritDoc}
     */
    public void leaveBand(String userName, String bandName) {
        query(getQuery("LEAVE_BAND"), bandName);
    }

    /**
     * {@inheritDoc}
     */
    public boolean inBand(String userName, String bandName) {
        return exists(getQuery("IN_BAND"), userName, bandName);
    }

}
