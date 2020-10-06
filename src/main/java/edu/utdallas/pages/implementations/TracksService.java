package edu.utdallas.pages.implementations;

import edu.utdallas.pages.services.IDataSource;
import edu.utdallas.pages.services.ITracksService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("TracksService")
public class TracksService extends DbService implements ITracksService {

    public TracksService(@Qualifier("DataSource") IDataSource dataSource) {
        super(dataSource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newTrack(String trackUploader, String trackName) {
        Database.query(getDataSource(),getQuery("NEW_TRACK"), Database.getUUID().toString(),
                trackUploader, trackName, Database.getTime());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSample(String trackKey, String clipKey, String time, String rack) {
        Database.query(getDataSource(),getQuery("ADD_SAMPLE"), Database.getUUID().toString(),
                trackKey, clipKey, time, rack);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeSample(String sampleKey) {
        Database.query(getDataSource(),getQuery("REMOVE_SAMPLE"), sampleKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveSample(String sampleKey, String rack, String time) {
        Database.query(getDataSource(),getQuery("MOVE_SAMPLE"), time, rack, sampleKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String retrieveSamples(String trackKey) {
        String[] column = {"sample_key","clip_key", "time", "rack"};
        return Database.retrieveAsJsonArr(getDataSource(),column,column,getQuery("RETRIEVE_USER_CLIPS"),trackKey);
    }
}
