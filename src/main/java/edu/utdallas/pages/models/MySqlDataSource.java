package edu.utdallas.pages.models;

import edu.utdallas.pages.Database;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service("DataSource")
public class MySqlDataSource implements IDataSource {

    private final Properties queries;
    private final BasicDataSource dataSource;

    public MySqlDataSource() {
        this.queries = Database.queries;
        this.dataSource = Database.dataSource;
    }

    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void closeStatement(Statement s) {
        if (s != null) {
            try {
                s.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public String getQuery(String key) {
        return queries.getProperty(key);
    }
}
