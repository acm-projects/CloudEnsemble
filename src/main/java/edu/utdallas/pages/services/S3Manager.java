package edu.utdallas.pages.services;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.springframework.lang.Nullable;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class S3Manager implements IS3Manager{

    private final IAWSBucket bucket;
    private final BasicAWSCredentials awsCredentials;

    public S3Manager(IAWSBucket bucket) {
        this.bucket = bucket;
        this.awsCredentials = new BasicAWSCredentials(bucket.getAccessID(),bucket.getAccessKey());
    }

    /**
     * Uploads a file to the bucket
     * @param fileName name(key) of file to upload
     * @param fileData the data of the file to upload
     */
    public void uploadFile(String fileName, byte[] fileData) {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();

        ObjectMetadata metaData = new ObjectMetadata();
        metaData.setContentLength(fileData.length);
        PutObjectRequest putRequest = new PutObjectRequest(bucket.getBucketName(), fileName, new ByteArrayInputStream(fileData), metaData);
        s3Client.putObject(putRequest);
    }

    /**
     * Deletes a file from the bucket
     * @param fileName name(key) of file to delete
     */
    public void deleteFile(String fileName) {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();

        s3Client.deleteObject(bucket.getBucketName(), fileName);
    }

    /**
     * Retrieves a file as a byte[] from S3 Bucket
     * @param fileName name(key) of the file to access
     * @return returns byte[] if found and null if not found
     */
    @Nullable
    public byte[] retrieveFile(String fileName) {
        System.out.println(awsCredentials);
        System.out.println(awsCredentials.getAWSAccessKeyId());
        System.out.println(awsCredentials.getAWSSecretKey());
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();

        S3Object s3Object = s3Client.getObject(new GetObjectRequest(bucket.getBucketName(),fileName));
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
