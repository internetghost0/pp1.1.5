package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.service.UserService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("madotsuki", "nikki", (byte) 15);
        userService.saveUser("lain", "iwakura", (byte) 16);
        userService.saveUser("rei", "ayanami", (byte) 17);
        userService.saveUser("[deleted]", "?", (byte) 100);
        userService.getAllUsers().stream().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
