

package service;

import dao.DAO;
import dao.HibernateFactory;
import dao.JDBCFactory;
import dao.UserDaoFactory;
import entities.User;

import java.util.List;

public class UserService {
    private static UserService instance;

    private UserService () {

    }

    public static UserService getInstance () {
        if(instance==null) {
            instance = new UserService();
        }
        return instance;
    }

    public int createUser(User user) {
        return getUserDAO().addUser(user);
    }

    public List<User> getAllUsers() {
       return getUserDAO().getAllUsers();
    }

    public void deleteUser(String login) {
        getUserDAO().deleteUser(login);
    }

    public User getUserByLogin(String login)  {
        return getUserDAO().getUserByLogin(login);
    }

    public User getUserById(Long id)  {
        return getUserDAO().getUserById(id);
    }

    public int updateUser(User existUser, User newUser) {
       return getUserDAO().updateUser(existUser, newUser);
    }

    private static DAO getUserDAO () {
      return   UserDaoFactory.getInstance().createDAO();
    }
}
