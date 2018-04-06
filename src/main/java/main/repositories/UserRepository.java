package main.repositories;

import main.entities.User;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class UserRepository extends MainRepository {

    public UserRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void save(User user) {
        Session currentSession = getSessionFactory().getCurrentSession();
        currentSession.save(user);
    }

    public User findByEmail(String email) {
        Session currentSession = getSessionFactory().getCurrentSession();
        Criteria criteria = currentSession.createCriteria(User.class);
        criteria.add(Restrictions.eq("email", email));
        return (User) criteria.uniqueResult();
    }

    public User findByLogin(String name) {
        Session currentSession = getSessionFactory().getCurrentSession();
        Criteria criteria = currentSession.createCriteria(User.class);
        criteria.add(Restrictions.eq("login", name));
        return (User) criteria.uniqueResult();
    }

    public User findUserByUuid(UUID userUuid) {
        Session currentSession = getSessionFactory().getCurrentSession();
        Criteria criteria = currentSession.createCriteria(User.class);
        criteria.setFetchMode("addresses", FetchMode.JOIN);
        criteria.setFetchMode("records", FetchMode.JOIN);
        criteria.add(Restrictions.eq("uuid", userUuid));
        return (User) criteria.uniqueResult();
    }

    public void mergeUser(User user) {
        getSessionFactory().getCurrentSession().merge(user);
    }

    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        Session currentSession = getSessionFactory().getCurrentSession();
        Query fromUser = currentSession.createQuery("FROM User");
        return (List<User>) fromUser.list();
    }

    @SuppressWarnings("unchecked")
    public void select() {
        String login = "awdawd";
        final Session currentSession = getSessionFactory().getCurrentSession();
        final SQLQuery sqlQuery = currentSession.createSQLQuery("SELECT * FROM user use index(login_idx)  WHERE  login LIKE :login");
        sqlQuery.setParameter("login", login);
        final List<User> list = sqlQuery.list();
        System.out.println(list);
    }

}
