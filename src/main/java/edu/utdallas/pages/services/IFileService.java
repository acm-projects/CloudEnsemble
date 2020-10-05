package edu.utdallas.pages.services;

import org.springframework.lang.Nullable;

public interface IFileService {

    /**
     * Uploads a new clip to the S3 Storage and adds a new clip entry to the database
     * @param user owner of the clip
     * @param name name of the clip
     * @param fileData data in the clip
     */
    void uploadClip(String user, String name, byte[] fileData);

    /**
     * Uploads a new picture to the S3 Storage and adds the profile pic to database
     * @param user owner of the profile pic
     * @param fileData data in the pic
     */
    void uploadPic(String user, byte[] fileData);

    /**
     * Retrieves the S3 key of a clip from the database
     * @param user owner of the clip
     */
    String retrievePicKey(String user);

    /**
     * Retrieves a profile pic from S3 bucket
     * @param user owner of pic
     */
    @Nullable
    byte[] retrievePicData(String user);

    /**
     * Retrieves the S3 key of a clip from the database
     * @param user owner of the clip
     * @param name name of the clip
     */
    String retrieveClipKey(String user, String name);

    /**
     * Retrieves the clip data from S3 bucket
     * @param user owner of file
     * @param name name of file
     */
    @Nullable
    byte[] retrieveClipData(String user, String name);

    /**
     * Deletes a clip from the S3 Storage and removes the entry from the database
     * @param user owner of the clip
     * @param name name of the clip
     */
    void deleteClip(String user, String name);
}
