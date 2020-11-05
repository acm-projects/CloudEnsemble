package edu.utdallas.pages.implementations;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import edu.utdallas.pages.services.IS3Bucket;
import edu.utdallas.pages.services.IS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service("S3Service")
public class S3Service implements IS3Service {

    private final IS3Bucket bucket;
    private final AmazonS3 s3Client;

    @Autowired
    public S3Service(@Qualifier("S3Bucket") IS3Bucket bucket) {
        this.bucket = bucket;
        AWSCredentials awsCredentials = new BasicAWSCredentials(bucket.getAccessID(),bucket.getAccessKey());
        this.s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
    }

    /**
     * {@inheritDoc}
     */
    public void uploadFile(String fileName, byte[] fileData) {
        ObjectMetadata metaData = new ObjectMetadata();
        metaData.setContentLength(fileData.length);
        PutObjectRequest putRequest = new PutObjectRequest(bucket.getBucketName(), fileName, new ByteArrayInputStream(fileData), metaData);
        s3Client.putObject(putRequest);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteFile(String fileName) {
        s3Client.deleteObject(bucket.getBucketName(), fileName);
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    public byte[] retrieveFile(String fileName) {
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
