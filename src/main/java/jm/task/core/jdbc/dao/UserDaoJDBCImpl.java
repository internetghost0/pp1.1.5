package jm.task.core.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

public class UserDaoJDBCImpl implements UserDao, AutoCloseable {
    private Connection conn;

    public UserDaoJDBCImpl() {
        conn = Util.getConnection();
        if (conn == null) {
            System.err.println("[!] Can't get connection from database");
            // im not sure is it good way to just exit the program or not
            System.exit(-1);
        }
    }

    @Override
    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("[!] Can't close the connection from database");
        }
    }

    @Override
    public void createUsersTable() {
        // postgresql does not have TINYINT or any one bit integer
        String query = """
                CREATE TABLE IF NOT EXISTS users (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR,
                    lastname VARCHAR,
                    age SMALLINT
                    )""";

        try (Statement statement = conn.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS users";
        try (Statement statement = conn.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            rollback();
        }

    }

    @Override
    public void removeUserById(long id) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE id = ?");
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            rollback();
        }

    }

    @Override
    public List<User> getAllUsers() {
        String query = "SELECT * FROM users";
        ArrayList<User> list = new ArrayList<>();
        try (Statement statement = conn.createStatement()) {
            statement.execute(query);
            ResultSet result = statement.getResultSet();
            while (result.next()) {
                User user = new User(result.getString("name"),
                        result.getString("lastname"),
                        result.getByte("age"));
                user.setId(result.getLong("id"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        String query = "TRUNCATE TABLE users";
        try (Statement statement = conn.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            rollback();
        }
    }

    private void rollback() {
        try {
            conn.rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
