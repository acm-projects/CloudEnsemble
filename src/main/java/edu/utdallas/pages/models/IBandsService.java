package edu.utdallas.pages.models;

import java.sql.SQLException;

public interface IBandsService {

    /**
     * Creates a new band
     * @param bandName name of band to create
     * @throws SQLException if a band fails to create - it is not unique
     */
    void newBand(String bandName) throws SQLException;

    /**
     * User joins a band
     * @param userName user joining
     * @param bandName name of band to join
     */
    void joinBand(String userName, String bandName);

    /**
     * User leaves a band
     * @param userName user joining
     * @param bandName name of band to join
     */
    void leaveBand(String userName, String bandName);

    /**
     * Determines if a user is in a band
     * @param userName userName of user
     * @param bandName name of band
     * @return true or false
     */
    boolean inBand(String userName, String bandName);

}
