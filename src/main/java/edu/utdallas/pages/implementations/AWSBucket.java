package edu.utdallas.pages.implementations;


import edu.utdallas.pages.services.IAWSBucket;
import org.springframework.stereotype.Component;

@Component("AWSBucket")
public class AWSBucket implements IAWSBucket {

    public static String ACCESS_ID = System.getenv("S3_ID");
    public static String ACCESS_KEY = System.getenv("S3_KEY");
    public static String BUCKET_NAME = System.getenv("BUCKET_NAME");

    @Override
    public String getAccessKey() {
        return ACCESS_KEY;
    }

    @Override
    public String getAccessID() {
        return ACCESS_ID;
    }

    @Override
    public String getBucketName() {
        return BUCKET_NAME;
    }
}
