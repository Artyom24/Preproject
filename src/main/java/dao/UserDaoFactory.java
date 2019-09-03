package dao;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public interface UserDaoFactory {

    static UserDaoFactory getInstance() {
        Properties properties = new Properties();
        try {
            properties.load(UserDaoFactory.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (properties.getProperty("connection")) {
            case "jdbc" :
                return new JDBCFactory();
            case "hibernate" :
                return new HibernateFactory();
        }
        return null;
    }

     DAO createDAO();
}
