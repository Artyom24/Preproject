package util;



import entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static Connection connection;
    private static SessionFactory sessionFactory;
    private static String url;

    static {
        url =   "jdbc:mysql://"
                +"localhost:"
                +"3306/"
                +"preproject_1?"
                +"user=root&"
                +"password=24061982&"
                +"serverTimezone=UTC";
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private DBHelper() {}

    public static Connection getConnection () {
        if(connection == null) {
            connection = getMySQLConnection();
        }
        return connection;
   }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    private static Connection getMySQLConnection() {
        try {
            Connection connection = DriverManager.getConnection(url);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static Configuration getConfiguration () {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/preproject_1?serverTimezone=UTC");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.jdbc.time_zone", "UTC");
        configuration.setProperty("hibernate.connection.password", "24061982");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        return configuration;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
