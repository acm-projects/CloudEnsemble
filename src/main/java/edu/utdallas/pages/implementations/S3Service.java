package edu.utdallas.pages.implementations;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import edu.utdallas.pages.services.IAWSBucket;
import edu.utdallas.pages.services.IS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service("S3Service")
public class S3Service implements IS3Service {

    private final IAWSBucket bucket;
    private final BasicAWSCredentials awsCredentials;

    @Autowired
    public S3Service(@Qualifier("AWSBucket") IAWSBucket bucket) {
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
