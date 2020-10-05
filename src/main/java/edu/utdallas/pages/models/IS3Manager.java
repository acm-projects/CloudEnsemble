package edu.utdallas.pages.models;

import org.springframework.lang.Nullable;

public interface IS3Manager {

    /**
     * Uploads a file to the bucket
     * @param fileName name(key) of file to upload
     * @param fileData the data of the file to upload
     */
    void uploadFile(String fileName, byte[] fileData);

    /**
     * Deletes a file from the bucket
     * @param fileName name(key) of file to delete
     */
    void deleteFile(String fileName);

    /**
     * Retrieves a file as a byte[] from S3 Bucket
     * @param fileName name(key) of the file to access
     * @return returns byte[] if found and null if not found
     */
    @Nullable
    byte[] retrieveFile(String fileName);

}
