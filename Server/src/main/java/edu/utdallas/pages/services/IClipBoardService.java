package edu.utdallas.pages.services;

import java.sql.SQLException;

public interface IClipBoardService {

    /**
     * Adds to the clip board
     * @param trackKey key of track
     * @param clipKey key of clip
     * @throws SQLException if there is a problem
     * @return the uuid for the new element
     */
    String addToClipBoard(String trackKey, String clipKey) throws SQLException;

    /**
     * Remove from the clip board
     * @param elementKey of element
     */
    void removeFromClipBoard(String elementKey);

    /**
     * Gets the clip board as a json
     * @param trackKey key of track
     * @return as json
     */
    String getFromClipBoard(String trackKey);

    /**
     * Moves element on a clip board
     * @param trackKey of element
     * @param eleKey to move
     * @param afterEle of the element after the location we are moving to,
     * should be empty if we are moving to the last position
     * @throws SQLException when a sql error occurs
     */
    void moveClipBoardElement(String trackKey, String eleKey, String afterEle) throws SQLException;
}
