package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String CONNECTION_URL = ConnectionData.MYSQL.getUrl();
    private static final String USERNAME = ConnectionData.MYSQL.getUsername();
    private static final String PASSWORD = ConnectionData.MYSQL.getPassword();

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
        return DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
    }
}
