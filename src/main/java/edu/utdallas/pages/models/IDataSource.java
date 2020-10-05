package edu.utdallas.pages.models;

import java.sql.Connection;
import java.sql.Statement;

public interface IDataSource {

    Connection getConnection();

    void closeConnection(Connection conn);

    void closeStatement(Statement s);

    String getQuery(String key);
}
