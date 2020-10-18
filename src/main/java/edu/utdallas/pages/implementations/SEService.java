package edu.utdallas.pages.implementations;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import edu.utdallas.pages.services.IS3Bucket;
import edu.utdallas.pages.services.ISESCredentials;
import edu.utdallas.pages.services.ISEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("SEService")
public class SEService implements ISEService {

    public static final String FROM = "noreply.CloudEnsemble@gmail.com";

    private final AmazonSimpleEmailService client;

    @Autowired
    public SEService(@Qualifier("SesCredentials") ISESCredentials credentials) {
        AWSCredentials awsCredentials = new BasicAWSCredentials(credentials.getAccessID(), credentials.getAccessKey());
        this.client = AmazonSimpleEmailServiceClientBuilder.standard().withRegion(Regions.US_EAST_2)
                        .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendEmail(String to, String message) {
        try {
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(to))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset("UTF-8").withData(message))
                                    .withText(new Content()
                                            .withCharset("UTF-8").withData(message)))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData("Subject")))
                    .withSource(FROM);
            client.sendEmail(request);
            System.out.println("Email sent!");
        } catch (Exception ex) {
            System.out.println("The email was not sent. Error message: "
                    + ex.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendVerificationEmail(String to, String code) {
        sendEmail(to,code);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendSuccessEmail(String to, String user) {
        sendEmail(to,user + ", Your Cloud Ensemble account registered successfully!");
    }

}
