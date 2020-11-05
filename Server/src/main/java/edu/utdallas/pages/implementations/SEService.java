package edu.utdallas.pages.implementations;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import edu.utdallas.pages.services.ISESCredentials;
import edu.utdallas.pages.services.ISEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("SEService")
public class SEService implements ISEService {

    public static final String FROM = "CloudEnsembleTeam@gmail.com";

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
    public void sendEmail(String to, String subject, String message) {
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
                                    .withCharset("UTF-8").withData(subject)))
                    .withSource(FROM);
            client.sendEmail(request);
        } catch (Exception ex) {
            System.out.println("The email was not sent. Error message: " + ex.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendPasswordResetEmail(String to, String link) {
        String message = "Greetings from Cloud Ensemble, <br><br>" +
                "We have received a request to reset your password for the Cloud Ensemble account associated with this email address. " +
                "If you sent the request to reset your password then click the link or copy paste it to your web browser: <br><br>" +
                link + "<br><br>"+
                "If you did not request to reset your password then you can safely ignore this email.<br><br>" +
                "Thank you for using Cloud Ensemble!";
        sendEmail(to,"Password Reset Request",message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendVerificationEmail(String to, String code) {
        String message = "Greetings from Cloud Ensemble, <br><br>" +
                "We have received a request authorize this email address for your account. " +
                "Here's your temporary code to verify your account: <br><br>" +
                "<font size=\"+3\">" + code + "</font><br>"+
                "If you did not send a request to authorize this email address then ignore this email.<br><br>" +
                "Sincerely, <br>" +
                "The Cloud Ensemble Team";
        sendEmail(to,"Email Address Verification Request",message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendVerificationSuccessEmail(String to, String user) {
        String message = "Greetings from Cloud Ensemble, <br><br>" +
                "Your Cloud Ensemble registration for \"" + user + "\" was successful! <br><br>" +
                "Welcome to Cloud Ensemble!";
        sendEmail(to,"Registration Successful",message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendResetSuccessEmail(String to) {
        String message = "Greetings from Cloud Ensemble, <br><br>" +
                "Your password reset was successful! <br><br>" +
                "Thank you for using Cloud Ensemble!";
        sendEmail(to,"Password Reset Successful",message);
    }

}
