package edu.utdallas.pages.implementations;

import com.fasterxml.uuid.Generators;
import edu.utdallas.pages.services.IDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    public static final String DB_NAME = System.getenv().get("DB_NAME");
    public static final String USERNAME = System.getenv().get("DB_USERNAME");
    public static final String PASSWORD = System.getenv().get("DB_PASSWORD");
    public static final String URL = System.getenv().get("DB_URL");

    public static final Properties queries = new Properties();
    public static final BasicDataSource dataSource = new BasicDataSource();

    static {
        InputStream stream = Database.class.getClassLoader().getResourceAsStream("queries.properties");
        try {
            queries.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String connectionURL = URL + DB_NAME + "?useSSL=false";
        dataSource.setUrl(connectionURL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
    }

    /**
     * Gets a suitable UUID for the database
     */
    public static UUID getUUID() {
        return Generators.timeBasedGenerator().generate();
    }

    /**
     * Gets a TimeStamp
     */
    public static String getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    /**
     * Performs an UNCHECKED string query that doesn't receive or return data: insert,update,delete
     * @param dataSource dataSource for connection
     * @param query to run
     * @param params needed for query
     * @throws SQLException if a query fails
     */
    public static void queryUnchecked(IDataSource dataSource, String query, String... params) throws SQLException {
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = null;
        if(conn != null) {
            try {
                ps = conn.prepareStatement(query);
                for(int i = 0; i < params.length; i++) {
                    ps.setString(i+1, params[i]);
                }
                ps.executeUpdate();
            } finally {
                dataSource.closeStatement(ps);
                dataSource.closeConnection(conn);
            }
        }
    }

    /**
     * Performs a string query that doesn't receive or return data: insert,update,delete
     * @param dataSource dataSource for connection
     * @param query to run
     * @param params needed for query
     */
    public static void query(IDataSource dataSource, String query, String... params) {
        try {
            queryUnchecked(dataSource, query, params);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retrieves an  element from the database
     * @param dataSource dataSource for connection
     * @param query to run
     * @param params needed for query
     */
    public static String retrieve(IDataSource dataSource, String column, String query, String... params) {
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = null;
        if(conn != null) {
            try {
                ps = conn.prepareStatement(query);
                for(int i = 0; i < params.length; i++) {
                    ps.setString(i+1, params[i]);
                }
                ResultSet rs = ps.executeQuery();
                ArrayList<String> retrieve = new ArrayList<>();
                while(rs.next()) {
                    retrieve.add(rs.getString(column));
                }
                rs.close();
                if(retrieve.size() == 1) {
                    return retrieve.get(0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                dataSource.closeStatement(ps);
                dataSource.closeConnection(conn);
            }
        }
        return "";
    }

    /**
     * Gets entries with a query and returns them as a 2d string list
     * @param dataSource dataSource for connection
     * @param columns the columns matching retrieval query
     * @param query to run
     * @param params to insert
     * @return followers in a 2d json
     */
    public static JSONArray retrieveAsJsonArrObj(IDataSource dataSource, String[] attributes, String[] columns, String query, String... params) {
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = null;
        if(conn != null) {
            try {
                ps = conn.prepareStatement(query);
                for(int i = 0; i < params.length; i++) {
                    ps.setString(i+1, params[i]);
                }
                ResultSet rs = ps.executeQuery();
                JSONArray arr = new JSONArray();
                while(rs.next()) {
                    JSONObject jsonObject = new JSONObject();
                    for(int i = 0; i < columns.length; i++) {
                        jsonObject.put(attributes[i], rs.getString(columns[i]));
                    }
                    arr.put(jsonObject);
                }
                rs.close();
                return arr;
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(query);
            } finally {
                dataSource.closeStatement(ps);
                dataSource.closeConnection(conn);
            }
        }
        return new JSONArray();
    }

    /**
     * Gets entries with a query and returns them as a 2d string list
     * @param dataSource dataSource for connection
     * @param columns the columns matching retrieval query
     * @param query to run
     * @param params to insert
     * @return followers in a 2d json
     */
    public static String retrieveAsJsonArr(IDataSource dataSource, String[] attributes, String[] columns, String query, String... params) {
        return retrieveAsJsonArrObj(dataSource, attributes, columns, query, params).toString();
    }

    /**
     * Sends a query to check if an element in the database already exists
     * @param dataSource dataSource for connection
     * @param query to run
     * @param params to insert
     * @return true if it already exists
     */
    public static boolean exists(IDataSource dataSource, String query, String... params) {
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = null;
        if(conn != null) {
            try {
                ps = conn.prepareStatement(query);
                for(int i = 0; i < params.length; i++) {
                    ps.setString(i+1, params[i]);
                }
                ResultSet rs = ps.executeQuery();
                int counter = 0;
                while(rs.next()) {
                    counter++;
                }
                rs.close();
                return counter > 0;
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                dataSource.closeStatement(ps);
                dataSource.closeConnection(conn);
            }
        }
        return true;
    }

}
