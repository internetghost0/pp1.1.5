package jm.task.core.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
        SessionFactory sf = Util.getSessionFactory();
        if (sf == null) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }

    private Statement executeQuery(String query) {
        try (Connection conn = Util.getConnection()) {
            Statement statement = conn.createStatement();
            statement.execute(query);
            return statement;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void createUsersTable() {
        // postgresql does not have TINYINT or any one bit integer
        String query = """
                CREATE TABLE IF NOT EXISTS users (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR,
                    lastname VARCHAR,
                    age SMALLINT
                    )""";
        executeQuery(query);
    }

    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS users";
        executeQuery(query);
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection conn = Util.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try (Connection conn = Util.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE id = ?");
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        createUsersTable();
        String query = "SELECT * FROM users";
        ArrayList<User> list = new ArrayList<>();
        try (Statement statement = executeQuery(query)) {
            ResultSet result = statement.getResultSet();
            while (result.next()) {
                list.add(new User(result.getString("name"),
                        result.getString("lastname"),
                        (byte) result.getInt("age")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        createUsersTable();
        executeQuery("TRUNCATE TABLE users");
    }
}
