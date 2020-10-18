package edu.utdallas.pages.implementations;

import edu.utdallas.pages.services.ISESCredentials;
import org.springframework.stereotype.Component;

@Component("SesCredentials")
public class SESCredentials implements ISESCredentials  {

    public static final String ACCESS_ID = System.getenv("SES_ID");
    public static final String ACCESS_KEY = System.getenv("SES_KEY");

    @Override
    public String getAccessKey() {
        return ACCESS_KEY;
    }

    @Override
    public String getAccessID() {
        return ACCESS_ID;
    }
}
