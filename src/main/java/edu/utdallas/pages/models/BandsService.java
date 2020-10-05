package edu.utdallas.pages.models;

import edu.utdallas.pages.Database;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service("BandsService")
public class BandsService implements IBandsService {

    private final IDataSource dataSource;

    public BandsService(@Qualifier("DataSource") IDataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * {@inheritDoc}
     */
    public void newBand(String bandName) throws SQLException {
        Database.queryUnchecked(dataSource, dataSource.getQuery("NEW_BAND"), bandName);
    }

    /**
     * {@inheritDoc}
     */
    public void joinBand(String userName, String bandName) {
        Database.query(dataSource, dataSource.getQuery("JOIN_BAND"), bandName);
    }

    /**
     * {@inheritDoc}
     */
    public void leaveBand(String userName, String bandName) {
        Database.query(dataSource, dataSource.getQuery("LEAVE_BAND"), bandName);
    }

    /**
     * {@inheritDoc}
     */
    public  boolean inBand(String userName, String bandName) {
        return Database.exists(dataSource, dataSource.getQuery("IN_BAND"), userName, bandName);
    }

}
