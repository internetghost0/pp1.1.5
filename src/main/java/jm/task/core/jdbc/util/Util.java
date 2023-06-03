package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import jm.task.core.jdbc.model.User;

public class Util {
    private final static String URL = "jdbc:postgresql://localhost:5432/firstdb";
    private final static String USER = "zer0";
    private final static String PASSWORD = "toor";
    private final static String DRIVER = "org.postgresql.Driver";

    private static SessionFactory sessionFactory;

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
        } catch (Exception e) {
            System.err.println(e);
        }
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Class.forName(DRIVER);
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, DRIVER);
                settings.put(Environment.URL, URL);
                settings.put(Environment.USER, USER);
                settings.put(Environment.PASS, PASSWORD);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL10Dialect");
                // settings.put(Environment.SHOW_SQL, "true");
                configuration.addAnnotatedClass(User.class);
                configuration.setProperties(settings);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return sessionFactory;
    }
}