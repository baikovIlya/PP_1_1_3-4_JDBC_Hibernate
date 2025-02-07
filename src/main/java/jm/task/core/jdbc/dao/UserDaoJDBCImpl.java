package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        try (Connection conn = Util.getConnection();
        Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255)," +
                    "lastName VARCHAR(255)," + "age TINYINT)");
        }
    }

    public void dropUsersTable() throws SQLException {
        try (Connection conn = Util.getConnection();
        Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS users");
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try (Connection conn = Util.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users " +
                     "(name, lastName, age) VALUES (?, ?, ?)")) {
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setByte(3, age);
            stmt.executeUpdate();
        }
    }

    public void removeUserById(long id) throws SQLException {
        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement()) {
            String sql = "DELETE FROM users WHERE id = " + id;
            stmt.executeUpdate(sql);
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                users.add(new User(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("lastName"),
                        rs.getByte("age")));
            }
        }
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("TRUNCATE TABLE users");
        }
    }
}
