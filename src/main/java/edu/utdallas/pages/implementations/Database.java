package edu.utdallas.pages.implementations;

import edu.utdallas.pages.controllers.SystemProperty;
import org.apache.commons.dbcp2.BasicDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Database {

    public static final String DB_NAME = SystemProperty.getenv("DB_NAME");
    public static final String USERNAME = SystemProperty.getenv("DB_USERNAME");
    public static final String PASSWORD = SystemProperty.getenv("DB_PASSWORD");
    public static final String URL = SystemProperty.getenv("DB_URL");

    public static final Properties queries = new Properties();
    public static final BasicDataSource dataSource = new BasicDataSource();

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        InputStream stream = Database.class.getClassLoader().getResourceAsStream("queries.properties");
        try {
            queries.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String connectionURL = URL + DB_NAME + "?useSSL=false&allowMultiQueries=true";
        System.out.println(connectionURL);
        dataSource.setUrl(connectionURL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
    }

}