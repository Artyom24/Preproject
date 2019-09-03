package dao;



import entities.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import util.DBHelper;


import java.util.List;

public class UserDAOHibernateImpl implements DAO {

    private Session session;

    public UserDAOHibernateImpl() {
        this.session = DBHelper.getSessionFactory().openSession();
    }

    public int addUser (User user) {
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.save(user);
        transaction.commit();
        return id==0 ? 0 : 1;

    }

    public List<User> getAllUsers () {
        List<User> users;
        Criteria criteria = session.createCriteria(User.class);
        users = (List<User>)criteria.list();
        return users;
    }

    public void deleteUser (String login) {
        Transaction transaction = session.beginTransaction();
        User user = getUserByLogin(login);
        session.delete(user);
        transaction.commit();
    }

    public User getUserById (Long id) {
        Criteria criteria = session.createCriteria(User.class);
        User user = (User)criteria.add(Restrictions.eq("id", id))
                .uniqueResult();
        return user;
    }

    public User getUserByLogin (String login) {
        Criteria criteria = session.createCriteria(User.class);
        User user = (User)criteria.add(Restrictions.eq("login", login))
                .uniqueResult();
        return user;
    }

    public int updateUser (User existUser, User newUser) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update User set name = :newName, login = :newLogin, password = :newPassword " +
                "where login = :existLogin");
        query.setParameter("newName", newUser.getName());
        query.setParameter("newLogin", newUser.getLogin());
        query.setParameter("newPassword", newUser.getPassword());
        query.setParameter("existLogin", existUser.getLogin());
        try {
            query.executeUpdate();
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            return 0;
        }
        transaction.commit();
        return 1;
    }
}
