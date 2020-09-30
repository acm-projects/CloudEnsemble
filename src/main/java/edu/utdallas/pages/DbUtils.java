package edu.utdallas.pages;

import com.fasterxml.uuid.Generators;
import org.apache.commons.dbcp2.BasicDataSource;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;

public class DbUtils {

    private static final String DB_NAME = System.getenv().get("DB_NAME");
    private static final String USERNAME = System.getenv().get("DB_USERNAME");
    private static final String PASSWORD = System.getenv().get("DB_PASSWORD");
    private static final String URL = System.getenv().get("DB_URL");

    private static final Properties queries = new Properties();
    private static final BasicDataSource dataSource = new BasicDataSource();

    private static final String DEFAULT_PROFILE_PIC_KEY = "default_profile_pic.png";

    static {
        InputStream stream = DbUtils.class.getClassLoader().getResourceAsStream("queries.properties");
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

    /** Sets a database connection and returns it
     * @return the database connection
     */
    @Nullable
    private static Connection getDbConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException ex) {
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
                Logger.getLogger(DbUtils.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(DbUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
     * Performs a string query that doesn't receive or return data: insert,update,delete
     * @param query to run
     * @param params needed for query
     */
    public static void query(String query, String... params) {
        Connection conn = getDbConnection();
        PreparedStatement ps = null;
        if(conn != null) {
            try {
                ps = conn.prepareStatement(query);
                for(int i = 0; i < params.length; i++) {
                    ps.setString(i+1, params[i]);
                }
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DbUtils.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeStatement(ps);
                closeDbConnection(conn);
            }
        }
    }

    /**
     * Retrieves an  element from the database
     * @param query to run
     * @param params needed for query
     */
    public static String retrieve(String column, String query, String... params) {
        Connection conn = getDbConnection();
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
                Logger.getLogger(DbUtils.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeStatement(ps);
                closeDbConnection(conn);
            }
        }
        return "";
    }

    /**
     * Gets entries with a query and returns them as a 2d string list
     * @param columns the columns matching retrieval query
     * @param query to run
     * @param params to insert
     * @return followers in a 2d json
     */
    public static String retrieveAsJsonArr(String[] attributes, String[] columns, String query, String... params) {
        Connection conn = getDbConnection();
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
                        jsonObject.put(attributes[i], rs.getString(columns[0]));
                    }
                    arr.put(jsonObject);
                }
                rs.close();
                return arr.toString();
            } catch (SQLException ex) {
                Logger.getLogger(DbUtils.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeStatement(ps);
                closeDbConnection(conn);
            }
        }
        return "";
    }

    /**
     * Sends a query to check if an element in the database already exists
     * @param query to run
     * @param params to insert
     * @return true if it already exists
     */
    public static boolean exists(String query, String... params) {
        Connection conn = getDbConnection();
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
                Logger.getLogger(DbUtils.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeStatement(ps);
                closeDbConnection(conn);
            }
        }
        return true;
    }

    /**
     * Registers a user into the database
     * @param email email for registration
     * @param username the username for registration
     * @param password password for registration
     */
    public static void register(String email, String username, String password) {
        String salt = HashManager.generateSalt();
        query(queries.getProperty("REGISTER"),username,
                email,DEFAULT_PROFILE_PIC_KEY,HashManager.hashString(password,salt),salt);
    }

    /**
     * Validates a login input is valid, returns unique username if login is successful
     * returns an empty string otherwise
     * @param email email for login
     * @param password password for login
     * @return user name login, empty string if login fails
     */
    public static String login(String email, String password) {
        return retrieve("user_name",queries.getProperty("LOGIN"),email,
                HashManager.hashString(password,getSalt(email)));
    }

    public static String getSalt(String email) {
        return retrieve("salt",queries.getProperty("RETRIEVE_SALT"),email);
    }

    /**
     * Checks if the email is already in the database for registration
     * @param email to check
     * @return true if email doesn't exist, false otherwise
     */
    public static boolean checkEmail(String email) {
        return !exists(queries.getProperty("CHECK_EMAIL"),email);
    }

    /**
     * Checks if the name is already in the database for registration
     * @param name to check
     * @return true if name doesn't exist, false otherwise
     */
    public static boolean checkName(String name) {
        return !exists(queries.getProperty("CHECK_NAME"),name);
    }

    /**
     * Uploads a new clip to the S3 Storage and adds a new clip entry to the database
     * @param user owner of the clip
     * @param name name of the clip
     * @param fileData data in the clip
     */
    public static void uploadClip(String user, String name, byte[] fileData) {
        String uuid = getUUID().toString();
        //Upload to S3 Bucket
        S3Manager.uploadFile(uuid,fileData);
        //Insert in database
        query(queries.getProperty("INSERT_CLIP"),uuid,user,name,getTime());
    }

    /**
     * Uploads a new picture to the S3 Storage and adds the profile pic to database
     * @param user owner of the profile pic
     * @param fileData data in the pic
     */
    public static void uploadPic(String user, byte[] fileData) {
        String uuid = getUUID().toString();
        //Upload to S3 Bucket
        S3Manager.uploadFile(uuid,fileData);
        //Insert in database
        query(queries.getProperty("ADD_PIC"),uuid,user);
    }

    /**
     * Retrieves the S3 key of a clip from the database
     * @param user owner of the clip
     */
    public static String retrievePicKey(String user) {
        return retrieve("pic_key",queries.getProperty("RETRIEVE_PIC"),user);
    }

    /**
     * Retrieves a profile pic from S3 bucket
     * @param user owner of pic
     */
    @Nullable
    public static byte[] retrievePicData(String user) {
        String path = retrievePicKey(user);
        if(!path.equals("")) {
            return S3Manager.retrieveFile(path);
        } else {
            return null;
        }
    }

    /**
     * Checks if a clip name is already taken
     * @param user the owner of the clip
     * @param name the name of the clip
     * @return true if it already exists
     */
    public static boolean clipExists(String user, String name) {
        return exists(queries.getProperty("RETRIEVE_CLIP"),user,name);
    }

    /**
     * Retrieves the S3 key of a clip from the database
     * @param user owner of the clip
     * @param name name of the clip
     */
    public static String retrieveClipKey(String user, String name) {
        return retrieve("clip_key",queries.getProperty("RETRIEVE_CLIP"),user,name);
    }

    /**
     * Retrieves the clip data from S3 bucket
     * @param user owner of file
     * @param name name of file
     */
    @Nullable
    public static byte[] retrieveClipData(String user, String name) {
        String path = retrieveClipKey(user,name);
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
        //Delete from S3 Bucket
        S3Manager.deleteFile(retrieveClipKey(user,name));
        //Delete from database
        query(queries.getProperty("DELETE_CLIP"),user,name);
    }

    /**
     * Makes a user follow another user
     * @param follower going to follow
     * @param following user to follow
     */
    public static void followUser(String follower, String following) {
        query(queries.getProperty("FOLLOW"),follower,following);
    }

    /**
     * Makes a user unfollow another user
     * @param follower going to unfollow
     * @param following user to unfollow
     */
    public static void unFollowUser(String follower, String following) {
        query(queries.getProperty("UNFOLLOW"),follower,following);
    }

    /**
     * Checks if a user if following another
     * @param follower might be follower
     * @param following might be followed
     */
    public static boolean isFollowing(String follower, String following) {
        return exists(queries.getProperty("IS_FOLLOWING"),follower,following);
    }

    /**
     * Gets all the followers for a certain member
     * @param member to get for
     * @return followers in a json arr
     */
    public static String retrieveFollowers(String member) {
        String[] column = {"follower"};
        return retrieveAsJsonArr(column,column,queries.getProperty("RETRIEVE_FOLLOWERS"),member);
    }

    /**
     * Gets who the member is following
     * @param member to get for
     * @return following in a json arr
     */
    public static String retrieveFollowing(String member) {
        String[] column = {"following"};
        return retrieveAsJsonArr(column,column,queries.getProperty("RETRIEVE_FOLLOWING"), member);
    }

    /**
     * Gets all of a user's clips
     * @param user to get for
     * @return clips in a json arr
     */
    public static String retrieveClips(String user) {
        String[] column = {"clip_key","clip_name"};
        String[] attributes = {"clip_key","clip_name"};
        return retrieveAsJsonArr(column, column,queries.getProperty("RETRIEVE_USER_CLIPS"),user);
    }

    /**
     * Adds a new UNIQUE tag to the list of all tags
     * @param tagId id of tag
     * @param type Artist, Genre, Instrument, Other
     */
    public static void newTag(String tagId, TagType type) {
        query(queries.getProperty("NEW_TAG"),tagId, "0", type.getId());
    }

    /**
     * Adds a new tag to a clip
     * @param clipKey key to identify clip
     * @param tagId id of tag to add
     */
    public static void addTag(String clipKey, String tagId) {
        query(queries.getProperty("ADD_TAG"), clipKey, tagId);
        query(queries.getProperty("INCREMENT_TAG"), tagId);
    }

    /**
     * Remove a tag from a clip
     * @param clipKey key to identify clip
     * @param tagId id of tag to remove
     */
    public static void removeTag(String clipKey, String tagId) {
        query(queries.getProperty("REMOVE_TAG"), clipKey, tagId);
        query(queries.getProperty("DECREMENT_TAG"), tagId);
    }

    /**
     * Remove all tags from a clip
     * @param clipKey key to identify clip
     */
    public static void removeAllTags(String clipKey) {
        query(queries.getProperty("REMOVE_ALL_TAGS"), clipKey);
        query(queries.getProperty("DECREMENT_ALL_TAGS"), clipKey);
    }

    /**
     * Retrieve all tags
     * @param clipKey tags for a clip
     * @return retrieve in a json arr
     */
    public static String retrieveTags(String clipKey) {
        String[] columns = {"tag_id"};
        return retrieveAsJsonArr(columns, columns, queries.getProperty("RETRIEVE_TAGS"), clipKey);
    }

    /**
     *  Deletes all credentials from the database
     */
    private static void deleteCredentials() {
        Connection conn = getDbConnection();
        PreparedStatement ps = null;
        if(conn != null) {
            try {
                ps = conn.prepareStatement(queries.getProperty("DELETE_CREDENTIALS"));
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DbUtils.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeStatement(ps);
                closeDbConnection(conn);
            }
        }
    }

    //To test database
    public static void main(String[] args) {
        //deleteCredentials();
        /*System.out.println(login("email_test","password_test"));
        boolean checkName = checkName("username_test");
        boolean checkEmail = checkEmail("email_test");
        System.out.println(checkName);
        System.out.println(checkEmail);
        if(checkName && checkEmail) {
            register("email_test", "username_test", "password_test");
        }
        System.out.println(login("email_test","password_test"));
        //register("another_email", "another_username", "password_test");
        followUser("username_test","another_user");
        System.out.println(isFollowing("username_test","another_user"));
        unFollowUser("username_test","another_user");*/
        //deleteClip("username_test","sound_test.wav");

        newTag("Mozart",TagType.Artist);
    }

}
