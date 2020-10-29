package edu.utdallas.pages.services;

import java.sql.SQLException;

public interface IBandsService {

    /**
     * Creates a new band
     * @param founder of band
     * @param bandName name of band to create
     * @throws SQLException if a band fails to create - it is not unique
     */
    void newBand(String founder, String bandName) throws SQLException;

    /**
     * User joins a band
     * @param userName user joining
     * @param bandId name of band to join
     */
    void joinBand(String userName, String bandId);

    /**
     * User leaves a band
     * @param userName user joining
     * @param bandId name of band to join
     */
    void leaveBand(String userName, String bandId);

    /**
     * Determines if a user is in a band
     * @param userName userName of user
     * @param bandId name of band
     * @return true or false
     */
    boolean inBand(String userName, String bandId);

}
