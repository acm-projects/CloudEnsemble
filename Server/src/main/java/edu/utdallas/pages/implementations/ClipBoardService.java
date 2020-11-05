package edu.utdallas.pages.implementations;

import edu.utdallas.pages.services.IClipBoardService;
import edu.utdallas.pages.services.IDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service("ClipBoardService")
public class ClipBoardService extends DbService implements IClipBoardService {

    public ClipBoardService(@Qualifier("DataSource") IDataSource dataSource) {
        super(dataSource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String addToClipBoard(String trackKey, String clipKey) throws SQLException {
        String uuid = getUUID();
        int val = retrieveValue("ele_order",getQuery("GET_NEXT_ORDER"),trackKey);
        queryUncaught(getQuery("ADD_TO_CLIPBOARD"),uuid,trackKey,clipKey,Integer.toString(val + 1));
        return uuid;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeFromClipBoard(String elementKey) {
        query(getQuery("REMOVE_FROM_CLIPBOARD"),elementKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFromClipBoard(String trackKey) {
        String[] column = {"element_key","clip_key"};
        return retrieveAsJsonArr("clip_board",column,column,getQuery("GET_FROM_CLIPBOARD"),trackKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveClipBoardElement(String trackKey, String eleKey, String afterEle) throws SQLException {
        String position = retrieve("ele_order",getQuery("GET_ELEMENT_POSITION"),afterEle);
        if(!position.isEmpty()) {
            queryUncaught(getQuery("INCREMENT_ELEMENTS"),trackKey,position);
            queryUncaught(getQuery("MOVE_ELEMENT"),position,eleKey);
        } else {
            int val = retrieveValue("ele_order",getQuery("GET_NEXT_ORDER"),trackKey) + 1;
            queryUncaught(getQuery("MOVE_ELEMENT"),Integer.toString(val),eleKey);
        }
    }

}
