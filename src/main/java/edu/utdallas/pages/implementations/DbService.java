package edu.utdallas.pages.implementations;

import edu.utdallas.pages.services.IDataSource;

public class DbService {

    private final IDataSource dataSource;

    public DbService(IDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public IDataSource getDataSource() {
        return dataSource;
    }

    public String getQuery(String key) {
        return dataSource.getQuery(key);
    }
}
