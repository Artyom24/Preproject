package dao;

import entities.User;
import util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBSImpl implements DAO {
    private Connection connection;

    public UserDaoJDBSImpl() {
        this.connection = DBHelper.getConnection();
    }

    public int addUser(User user)  {
        try {
            PreparedStatement pS = connection.prepareStatement("insert into users (name, password, login, role) values (?, ?, ?, ?)");
            pS.setString(1, user.getName());
            pS.setString(2, user.getPassword());
            pS.setString(3, user.getLogin());
            pS.setString(4, user.getRole());
            return pS.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }


    }

    public User getUserByLogin(String login)  {
        try {
            PreparedStatement pS = connection.prepareStatement("select * from users where login = ?");
            pS.setString(1, login);
            ResultSet result = pS.executeQuery();
            result.next();
            User user = new User(
                    result.getLong(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            );
            result.close();
            pS.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserById(Long Id) {
        try {
            PreparedStatement pS = connection.prepareStatement("select * from users where id = ?");
            pS.setLong(1, Id);
            ResultSet result = pS.executeQuery();
            result.next();
            User user = new User(
                    result.getLong(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)
            );
            result.close();
            pS.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("select * from users");
            ResultSet result = stmt.getResultSet();
            while(result.next()) {
                User user = new User (
                        result.getLong(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4)

                );
                users.add(user);
            }
            result.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void deleteUser(String login) {
        try {
            PreparedStatement statement = connection.prepareStatement("delete from users where login = ?");
            statement.setString(1, login);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int updateUser(User existUser, User newUser) {
        try {
            PreparedStatement statement = connection.prepareStatement("update users set name = ?, login = ?, password = ?, role = ? where login like ?");
            statement.setString(1, newUser.getName());
            statement.setString(2, newUser.getLogin());
            statement.setString(3, newUser.getPassword());
            statement.setString(4, newUser.getRole());
            statement.setString(5, existUser.getLogin());
            statement.execute();
            statement.close();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

}

