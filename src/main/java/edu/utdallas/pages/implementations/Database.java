package edu.utdallas.pages.implementations;

import org.apache.commons.dbcp2.BasicDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
        String connectionURL = URL + DB_NAME + "?useSSL=false&allowMultiQueries=true";
        dataSource.setUrl(connectionURL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
    }

}