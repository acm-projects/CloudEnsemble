package edu.utdallas.pages;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.springframework.lang.Nullable;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class S3Manager {

    public static String ACCESS_ID = System.getenv("S3_ID");
    public static String ACCESS_KEY = System.getenv("S3_KEY");
    public static String BUCKET_NAME = System.getenv("BUCKET_NAME");

    /**
     * Uploads a file to the bucket
     * @param fileName name(key) of file to upload
     * @param fileData the data of the file to upload
     */
    public static void uploadFile(String fileName, byte[] fileData) {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_ID,ACCESS_KEY);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();

        ObjectMetadata metaData = new ObjectMetadata();
        metaData.setContentLength(fileData.length);
        PutObjectRequest putRequest = new PutObjectRequest(BUCKET_NAME, fileName, new ByteArrayInputStream(fileData), metaData);
        s3Client.putObject(putRequest);
    }

    /**
     * Deletes a file from the bucket
     * @param fileName name(key) of file to delete
     */
    public static void deleteFile(String fileName) {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_ID,ACCESS_KEY);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();

        s3Client.deleteObject(BUCKET_NAME, fileName);
    }

    /**
     * Retrieves a file as a byte[] from S3 Bucket
     * @param fileName name(key) of the file to access
     * @return returns byte[] if found and null if not found
     */
    @Nullable
    public static byte[] retrieveFile(String fileName) {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_ID,ACCESS_KEY);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();

        S3Object s3Object = s3Client.getObject(new GetObjectRequest(BUCKET_NAME,fileName));
        S3ObjectInputStream stream = s3Object.getObjectContent();
        try {
            byte[] bytes = stream.readAllBytes();
            stream.close();
            s3Object.close();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
