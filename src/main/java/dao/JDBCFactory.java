package dao;

public class JDBCFactory implements UserDaoFactory {


    public DAO createDAO() {
         return new UserDAOHibernateImpl();
    }
}
