package edu.utdallas.pages.implementations;

import edu.utdallas.pages.services.ICredentialsService;
import edu.utdallas.pages.services.IDataSource;
import edu.utdallas.pages.services.IHashService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("CredentialsService")
public class CredentialsService implements ICredentialsService {

    private static final String DEFAULT_PROFILE_PIC_KEY = "default_profile_pic.png";

    private final IDataSource dataSource;
    private final IHashService hashService;

    public CredentialsService(@Qualifier("DataSource") IDataSource dataSource, @Qualifier("HashService") IHashService hashService) {
        this.dataSource = dataSource;
        this.hashService = hashService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void register(String email, String username, String password) {
        String salt = hashService.generateSalt();
        Database.query(dataSource,dataSource.getQuery("REGISTER"),username,
                email,DEFAULT_PROFILE_PIC_KEY, hashService.hashString(password,salt),salt);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String login(String email, String password) {
        return Database.retrieve(dataSource,"user_name",dataSource.getQuery("LOGIN"),email,
                hashService.hashString(password,getSalt(email)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSalt(String email) {
        return Database.retrieve(dataSource,"salt",dataSource.getQuery("RETRIEVE_SALT"),email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkEmail(String email) {
        return !Database.exists(dataSource,dataSource.getQuery("CHECK_EMAIL"),email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkName(String name) {
        return !Database.exists(dataSource,dataSource.getQuery("CHECK_NAME"),name);
    }
}
