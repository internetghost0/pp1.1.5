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
    private final static String USERNAME = "zer0";
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
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // dont quite understand the task, so implemented 2 ways of configuration
    public static SessionFactory getSessionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (Exception e) {
            System.err.println(e);
        }
        if (sessionFactory == null) { 
            Configuration configuration = new Configuration();
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, DRIVER);
            settings.put(Environment.URL, URL);
            settings.put(Environment.USER, USERNAME);
            settings.put(Environment.PASS, PASSWORD);
            settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
            configuration.setProperties(settings);
            configuration.addAnnotatedClass(User.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } 
        return sessionFactory;
    }
    /*
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Class.forName(DRIVER);
                Configuration configuration = new Configuration();
                configuration.addAnnotatedClass(User.class);
                configuration.configure("/jm/task/core/jdbc/util/hibernate.cfg.xml");
                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return sessionFactory;
    }
    */
}