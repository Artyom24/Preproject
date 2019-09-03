package dao;

import entities.User;

import java.util.List;

public interface DAO {

    int addUser(User user);

    User getUserByLogin(String login);

    User getUserById(Long Id);

    List<User> getAllUsers();

    void deleteUser(String login);

    int updateUser(User existUser, User newUser);

}
