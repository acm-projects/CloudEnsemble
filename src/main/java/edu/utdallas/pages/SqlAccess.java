package edu.utdallas.pages;

import org.springframework.lang.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;

public class SqlAccess {

    private static final String DB_NAME = System.getenv().get("DB_NAME");
    private static final String USERNAME = System.getenv().get("DB_USERNAME");
    private static final String PASSWORD = System.getenv().get("DB_PASSWORD");
    private static final String URL = System.getenv().get("DB_URL");

    private static final Properties queries = new Properties();

    static {
        InputStream stream = SqlAccess.class.getClassLoader().getResourceAsStream("queries.properties");
        try {
            queries.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Sets a database connection and returns it
     * @return the database connection
     */
    @Nullable
    private static Connection establishDbConnection() {
        String connectionURL = URL + DB_NAME + "?useSSL=false";
        try {
            return DriverManager.getConnection(connectionURL, USERNAME, PASSWORD);
        } catch (SQLException err) {
            Logger.getLogger(SqlAccess.class.getName()).log(Level.SEVERE, null, err);
            return null;
        }
    }

     /**
     * Closes a connection
     * @param conn the connection to close
     */
    private static void closeDbConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SqlAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Closes a statement
     * @param s the statement to close
     */
    private static void closeStatement(Statement s) {
        if (s != null) {
            try {
                s.close();
            } catch (SQLException ex) {
                Logger.getLogger(SqlAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Validates a login input is valid, returns unique username login if found
     * returns an empty string otherwise
     * @param email email for registration
     * @param username the username for registration
     * @param password password for registration
     */
    public static void register(String email, String username, String password) {
        Connection conn = establishDbConnection();
        PreparedStatement ps = null;
        if(conn != null) {
            try {
                ps = conn.prepareStatement(queries.getProperty("REGISTER"));
                ps.setString(1, email);
                ps.setString(2, username);
                ps.setString(3, password);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(SqlAccess.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeStatement(ps);
                closeDbConnection(conn);
            }
        }
    }

    /**
     * Validates a login input is valid, returns unique username if login is successful
     * returns an empty string otherwise
     * @param email email for login
     * @param password password for login
     * @return user name login, empty string if login fails
     */
    public static String login(String email, String password) {
        String[] column = {"user_name"};
        Connection conn = establishDbConnection();
        PreparedStatement ps = null;
        if(conn != null) {
            try {
                ps = conn.prepareStatement(queries.getProperty("LOGIN"));
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                ArrayList<String> userNames = new ArrayList<>();
                while(rs.next()) {
                    userNames.add(rs.getString(column[0]));
                }
                if(userNames.size() == 1) {
                    return userNames.get(0);
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(SqlAccess.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeStatement(ps);
                closeDbConnection(conn);
            }
        }
        return "";
    }

    /**
     * Checks if the email is already in the database for registration
     * @param email to check
     * @return true if email doesn't exist, false otherwise
     */
    public static boolean checkEmail(String email) {
        Connection conn = establishDbConnection();
        PreparedStatement ps = null;
        if(conn != null) {
            try {
                ps = conn.prepareStatement(queries.getProperty("CHECK_EMAIL"));
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                int counter = 0;
                while(rs.next()) {
                    counter++;
                }
                rs.close();
                return counter == 0;
            } catch (SQLException ex) {
                Logger.getLogger(SqlAccess.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeStatement(ps);
                closeDbConnection(conn);
            }
        }
        return false;
    }

    /**
     * Checks if the name is already in the database for registration
     * @param name to check
     * @return true if name doesn't exist, false otherwise
     */
    public static boolean checkName(String name) {
        Connection conn = establishDbConnection();
        PreparedStatement ps = null;
        if(conn != null) {
            try {
                ps = conn.prepareStatement(queries.getProperty("CHECK_NAME"));
                ps.setString(1, name);
                ResultSet rs = ps.executeQuery();
                int counter = 0;
                while(rs.next()) {
                    counter++;
                }
                rs.close();
                return counter == 0;
            } catch (SQLException ex) {
                Logger.getLogger(SqlAccess.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeStatement(ps);
                closeDbConnection(conn);
            }
        }
        return false;
    }

    /**
     * Uploads a new clip to the S3 Storage and adds a new clip entry to the database
     * @param user owner of the clip
     * @param name name of the clip
     * @param fileData data in the clip
     */
    public static void uploadClip(String user, String name, byte[] fileData) {
        Connection conn = establishDbConnection();
        PreparedStatement ps = null;
        if(conn != null) {
            try {
                int index = name.lastIndexOf('.');
                String nameNoType = name.substring(0,index);
                String nameType = name.substring(index);
                String fileName = user+"."+nameNoType+"."+System.currentTimeMillis()+nameType;
                //Upload to S3 Bucket
                S3Manager.uploadFile(fileName,fileData);
                //Insert in database
                ps = conn.prepareStatement(queries.getProperty("INSERT_CLIP"));
                ps.setString(1, user);
                ps.setString(2, nameNoType);
                ps.setString(3, fileName);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(SqlAccess.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeStatement(ps);
                closeDbConnection(conn);
            }
        }
    }

    /**
     * Checks if a clip name is already taken
     * @param user the owner of the clip
     * @param name the name of the clip
     * @return true if it already exists
     */
    public static boolean clipExists(String user, String name) {
        String[] column = {"clip_path"};
        Connection conn = establishDbConnection();
        PreparedStatement ps = null;
        if(conn != null) {
            try {
                int index = name.lastIndexOf('.');
                String nameNoType = name.substring(0,index);
                ps = conn.prepareStatement(queries.getProperty("RETRIEVE_CLIP"));
                ps.setString(1, user);
                ps.setString(2, nameNoType);
                ResultSet rs = ps.executeQuery();
                ArrayList<String> clipPaths = new ArrayList<>();
                while(rs.next()) {
                    clipPaths.add(rs.getString(column[0]));
                }
                rs.close();
                return clipPaths.size() >= 1;
            } catch (SQLException ex) {
                Logger.getLogger(SqlAccess.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeStatement(ps);
                closeDbConnection(conn);
            }
        }
        return true;
    }

    /**
     * Retrieves the S3 path of a clip from the database
     * @param user owner of the clip
     * @param name name of the clip
     */
    public static String retrieveClipPath(String user, String name) {
        String[] column = {"clip_path"};
        Connection conn = establishDbConnection();
        PreparedStatement ps = null;
        if(conn != null) {
            try {
                ps = conn.prepareStatement(queries.getProperty("RETRIEVE_CLIP"));
                ps.setString(1, user);
                ps.setString(2, name);
                ResultSet rs = ps.executeQuery();
                ArrayList<String> clipPaths = new ArrayList<>();
                while(rs.next()) {
                    clipPaths.add(rs.getString(column[0]));
                }
                rs.close();
                if(clipPaths.size() == 1) {
                    return clipPaths.get(0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(SqlAccess.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeStatement(ps);
                closeDbConnection(conn);
            }
        }
        return "";
    }

    /**
     * Retrieves the clip data from S3 bucket
     * @param user owner of file
     * @param name name of file
     */
    @Nullable
    public static byte[] retrieveClipData(String user, String name) {
        String path = retrieveClipPath(user,name);
        if(!path.equals("")) {
            return S3Manager.retrieveFile(path);
        } else {
            return null;
        }
    }

    /**
     * Deletes a clip from the S3 Storage and removes the entry from the database
     * @param user owner of the clip
     * @param name name of the clip
     */
    public static void deleteClip(String user, String name) {
        Connection conn = establishDbConnection();
        PreparedStatement ps = null;
        if(conn != null) {
            try {
                //Delete from S3 Bucket
                S3Manager.deleteFile(retrieveClipPath(user,name));
                //Delete from database
                ps = conn.prepareStatement(queries.getProperty("DELETE_CLIP"));
                ps.setString(1, user);
                ps.setString(2, name);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(SqlAccess.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeStatement(ps);
                closeDbConnection(conn);
            }
        }
    }

    /**
     *  Deletes all credentials from the database
     */
    private static void deleteCredentials() {
        Connection conn = establishDbConnection();
        PreparedStatement ps = null;
        if(conn != null) {
            try {
                ps = conn.prepareStatement(queries.getProperty("DELETE_CREDENTIALS"));
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(SqlAccess.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeStatement(ps);
                closeDbConnection(conn);
            }
        }
    }

    //To test database
    public static void main(String[] args) {
        deleteCredentials();
        System.out.println(login("email_test","password_test"));
        boolean checkName = checkName("username_test");
        boolean checkEmail = checkEmail("email_test");
        System.out.println(checkName);
        System.out.println(checkEmail);
        if(checkName && checkEmail) {
            register("email_test", "username_test", "password_test");
        }
        System.out.println(login("email_test","password_test"));
        deleteClip("username_test","testing");
    }

}
