package edu.utdallas.pages.services;

public interface ITracksService {

    /**
     * Get all the tracks as a json for a certain user
     * @param user to get tracks for
     * @return all tracks
     */
    String retrieveTracks(String user);

    /**
     * Create a new track
     * @param trackUploader uploader
     * @param trackName name of track
     */
    void newTrack(String trackUploader, String trackName);

    /**
     * Add a sample to a track
     * @param trackKey track to add to
     * @param clipKey sound of the sample
     * @param time time in track (in milliseconds)
     * @param rack rack to be placed (starts from 0)
     */
    void addSample(String trackKey, String clipKey, String time, String rack);

    /**
     * Removes a sample from a track
     * @param sampleKey the sample to remove
     */
    void removeSample(String sampleKey);

    /**
     * Moves a sample to a different rack and time
     * @param sampleKey sample to move
     * @param rack rack to move to (starts from 0)
     * @param time time to move to (in milliseconds)
     */
    void moveSample(String sampleKey, String rack, String time);

    /**
     * Retrieve all samples for a track
     * @param trackKey track to retrieve for
     * @return JSON with all samples
     */
    String retrieveSamples(String trackKey);

    /**
     * Checks if a track key exists
     * @param key the key of the clip
     * @return true if it exists
     */
    boolean trackKeyExists(String key);

}
