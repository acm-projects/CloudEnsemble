package edu.utdallas.pages.implementations;

import edu.utdallas.pages.services.AccessLevel;
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
    public String retrieveTracks(String user) {
        String[] column = {"track_key","track_name"};
        return retrieveAsJsonArr("tracks",column,column,getQuery("RETRIEVE_USER_TRACKS"),user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newTrack(String trackUploader, String trackName) {
        query(getQuery("INSERT_TRACK"), getUUID(), trackUploader, trackName, getTime(), AccessLevel.RESTRICTED.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSample(String trackKey, String clipKey, String time, String rack) {
        query(getQuery("ADD_SAMPLE"), getUUID(), trackKey, clipKey, time, rack);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeSample(String sampleKey) {
        query(getQuery("REMOVE_SAMPLE"), sampleKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveSample(String sampleKey, String time, String rack) {
        query(getQuery("MOVE_SAMPLE"), time, rack, sampleKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String retrieveSamples(String trackKey) {
        String[] column = {"sample_key","clip_key", "time", "rack"};
        return retrieveAsJsonArr("samples",column,column,getQuery("RETRIEVE_SAMPLES"),trackKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean trackKeyExists(String key) {
        return exists(getQuery("RETRIEVE_TRACK_KEY"),key);
    }
}
