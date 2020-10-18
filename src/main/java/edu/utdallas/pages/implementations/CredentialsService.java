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
        queryUncaught(getQuery("REGISTER"),username,
                email,DEFAULT_PROFILE_PIC_KEY, hashedPassword, salt);
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
        return !exists(getQuery("CHECK_EMAIL"),email) && result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkName(String name) {
        return !exists(getQuery("CHECK_NAME"),name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkPassword(String password) {
        return true;
    }
}
