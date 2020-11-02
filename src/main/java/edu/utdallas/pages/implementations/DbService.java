package edu.utdallas.pages.implementations;

import com.fasterxml.uuid.Generators;
import edu.utdallas.pages.services.IDataSource;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbService {

    private final IDataSource dataSource;

    public final Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);

    public DbService(IDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public IDataSource getDataSource() {
        return dataSource;
    }

    public String getQuery(String key) {
        return dataSource.getQuery(key);
    }

    /**
     * Cleans an input string of XSS to display in html
     * @param str input to clean
     * @return cleaned string (safe to be used in html)
     */
    public String cleanString(String str) {
        Document document = Jsoup.parse(str);
        document.outputSettings(outputSettings);
        document.select("br").append("\\n");
        document.select("p").prepend("\\n\\n");
        String s = document.html().replaceAll("\\\\n", "\n");
        return Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
    }

    /**
     * Gets a suitable UUID for the database
     */
    public String getUUID() {
        return Generators.timeBasedGenerator().generate().toString() + Thread.currentThread().getId();
    }

    /**
     * Gets a TimeStamp
     */
    public String getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    /**
     * Performs an UNCAUGHT string query that doesn't receive or return data: insert,update,delete
     * @param query to run
     * @param params needed for query
     * @throws SQLException if a query fails
     */
    public void queryUncaught(String query, String... params) throws SQLException {
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
     * @param query to run
     * @param params needed for query
     */
    public void query(String query, String... params) {
        try {
            queryUncaught(query, params);
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retrieves an  element from the database
     * @param query to run
     * @param params needed for query
     * @return value if found, empty string otherwise
     */
    public String retrieve(String column, String query, String... params) {
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = null;
        if(conn != null) {
            try {
                ps = conn.prepareStatement(query);
                for(int i = 0; i < params.length; i++) {
                    ps.setString(i+1, params[i]);
                }
                ResultSet rs = ps.executeQuery();
                String retrieve = rs.next() ? rs.getString(column) : "";
                rs.close();
                return retrieve;
            } catch (SQLException ex) {
                System.out.println("here");
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            } finally {
                dataSource.closeStatement(ps);
                dataSource.closeConnection(conn);
            }
        }
        return "";
    }

    /**
     * Retrieves an  element from the database
     * @param query to run
     * @param params needed for query
     * @return value if found, empty string otherwise
     */
    public int retrieveValue(String column, String query, String... params) {
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = null;
        if(conn != null) {
            try {
                ps = conn.prepareStatement(query);
                for(int i = 0; i < params.length; i++) {
                    ps.setString(i+1, params[i]);
                }
                ResultSet rs = ps.executeQuery();
                int retrieve = rs.next() ? rs.getInt(column) : 0;
                rs.close();
                return retrieve;
            } catch (SQLException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            } finally {
                dataSource.closeStatement(ps);
                dataSource.closeConnection(conn);
            }
        }
        return 0;
    }

    /**
     * Gets entries with a query and returns them as a 2d string list
     * @param columns the columns matching retrieval query
     * @param query to run
     * @param params to insert
     * @return info in a 2d json
     */
    public JSONArray retrieveAsJsonArrObj(String[] attributes, String[] columns, String query, String... params) {
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
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            } finally {
                dataSource.closeStatement(ps);
                dataSource.closeConnection(conn);
            }
        }
        return new JSONArray();
    }

    /**
     * Gets entries with a query and returns them as a 2d string list
     * @param arr name of array
     * @param columns the columns matching retrieval query
     * @param query to run
     * @param params to insert
     * @return followers in a 2d json
     */
    public String retrieveAsJsonArr(String arr, String[] attributes, String[] columns, String query, String... params) {
        JSONObject obj = new JSONObject();
        obj.put(arr,retrieveAsJsonArrObj(attributes, columns, query, params));
        return obj.toString();
    }

    /**
     * Sends a query to check if an element in the database already exists
     * @param query to run
     * @param params to insert
     * @return true if it already exists
     */
    public boolean exists(String query, String... params) {
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
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            } finally {
                dataSource.closeStatement(ps);
                dataSource.closeConnection(conn);
            }
        }
        return true;
    }

}