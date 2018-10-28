package com.rest.api.jdbcjpa.jdbc.dbconfig;

import br.org.configparser.ConfigParser;
import com.rest.api.jdbcjpa.jdbc.exception.DAOException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataSource {

    private static final String MESSAGE_URL_NOT_FOUND = "url not found!";
    private static final String MESSAGE_USERNAME_NOT_FOUND = "username not found!";
    private static final String MESSAGE_PASSWORD_NOT_FOUND = "password not found!";
    private static final String MESSAGE_DRIVER_NOT_FOUND = "driver not found!";

    private String url;
    private String username;
    private String password;
    private String driver;

    public void load(String path, String section) {
        path = path == null ? "" : path;
        section = section == null ? "" : section;
        if (!path.isEmpty()) {
            ConfigParser configParser = ConfigParser.configParser();
            configParser.read(path);
            if (section.isEmpty()) {
                url = configParser.get("url").toString();
                username = configParser.get("username").toString();
                password = configParser.get("password").toString();
                driver = configParser.get("driver").toString();
            } else {
                url = configParser.get(section, "url").toString();
                Object user = configParser.get(section, "username");
                if (user != null) {
                    username = user.toString();
                }

                Object pass = configParser.get(section, "password");
                if (user != null) {
                    password = pass.toString();
                }
                driver = configParser.get(section, "driver").toString();
            }
        }
    }

    public void load(String path) {
        load(path, null);
    }

    public static DataSource get(String path, String section) {
        DataSource dataSource = DataSource.builder().build();
        dataSource.load(path, section);
        return dataSource;
    }

    public static DataSource get(String path) {
        return get(path, null);
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            // carrega o driver na memória.
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Connection connection() {
        return getConnection();
    }

    private static void validateParams(String url, String username, String password, String driver) throws DAOException {
        url = url == null ? "" : url;
        username = username == null ? "" : username;
        password = password == null ? "" : password;
        driver = driver == null ? "" : driver;
        if (url.isEmpty()) {
            throw new DAOException(MESSAGE_URL_NOT_FOUND);
        }
        if (username.isEmpty()) {
            throw new DAOException(MESSAGE_USERNAME_NOT_FOUND);
        }
        if (password.isEmpty()) {
            throw new DAOException(MESSAGE_PASSWORD_NOT_FOUND);
        }
        if (driver.isEmpty()) {
            throw new DAOException(MESSAGE_DRIVER_NOT_FOUND);
        }
    }

    public static Connection connect(String url, String username, String password, String driver) throws DAOException {
        Connection connection = null;
        validateParams(url, username, password, driver);
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void disconnect(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
//            connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
