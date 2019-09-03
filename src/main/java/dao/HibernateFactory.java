package dao;

public class HibernateFactory implements UserDaoFactory {

    public DAO createDAO() {
        return new UserDAOHibernateImpl();
    }
}
