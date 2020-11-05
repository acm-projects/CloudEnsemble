package edu.utdallas.pages.implementations;

import edu.utdallas.pages.services.IDataSource;
import edu.utdallas.pages.services.IProfileService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("ProfileService")
public class ProfileService extends DbService implements IProfileService {

    public ProfileService(@Qualifier("DataSource") IDataSource dataSource) {
        super(dataSource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeDescription(String username, String desc) {
        query(getQuery("UPDATE_DESC"),desc,username);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String retrieveDescription(String username) {
        String[] columns = {"description"};
        return retrieveAsJsonArr("profile",columns,columns,getQuery("GET_DESC"),username);
    }

}
