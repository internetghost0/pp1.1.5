package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
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

    // dont quite understand the task, so implemented 2 ways of configuration
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) { 
            Configuration configuration = new Configuration(); 
            configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver"); 
            configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/mydatabase"); 
            configuration.setProperty("hibernate.connection.username", "myusername"); 
            configuration.setProperty("hibernate.connection.password", "mypassword"); 
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect"); 
            configuration.setProperty("hibernate.show_sql", "true"); 
             
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