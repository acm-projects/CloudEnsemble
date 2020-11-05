package edu.utdallas.pages.implementations;

import edu.utdallas.pages.services.ICredentialsService;
import edu.utdallas.pages.services.IDataSource;
import edu.utdallas.pages.services.IHashService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.sql.SQLException;

@Service("CredentialsService")
public class CredentialsService extends DbService implements ICredentialsService {

    private static final String DEFAULT_DESC = "Hello, I'm new to Cloud Ensemble!";
    private static final String DEFAULT_PROFILE_PIC_KEY = "default_profile_pic.png";

    private final IHashService hashService;

    public CredentialsService(@Qualifier("DataSource") IDataSource dataSource, @Qualifier("HashService") IHashService hashService) {
        super(dataSource);
        this.hashService = hashService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void register(String email, String username, String hashedPassword, String salt) throws SQLException {
        queryUncaught(getQuery("ADD_PROFILE"),username,DEFAULT_PROFILE_PIC_KEY,DEFAULT_DESC);
        queryUncaught(getQuery("REGISTER"),username,email,hashedPassword,salt);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String login(String email, String password) {
        return retrieve("user_name",getQuery("LOGIN"),email,
                hashService.hashString(password,getSalt(email)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetPassword(String email, String newPassword) {
        query(getQuery("RESET_PASSWORD"),hashService.hashString(newPassword,getSalt(email)),email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSalt(String email) {
        return retrieve("salt",getQuery("RETRIEVE_SALT"),email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkEmail(String email) {
        boolean result = true;
        try {
            InternetAddress address = new InternetAddress(email);
            address.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return !emailExists(email) && result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean emailExists(String email) {
        return exists(getQuery("CHECK_EMAIL"),email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkName(String name) {
        return !exists(getQuery("CHECK_NAME"),name) && name.length() >= 5 && name.length() <= 40;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkPassword(String password) {
        int len = password.length();
        return len >= 5 && len <= 40;
    }

}
