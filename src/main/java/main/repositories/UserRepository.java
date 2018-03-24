package main.repositories;

import main.entities.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
        criteria.add(Restrictions.eq("uuid", userUuid));
        User user = (User) criteria.uniqueResult();
        return user;
    }

    public void mergeUser(User user) {
        getSessionFactory().getCurrentSession().merge(user);
    }

    public List<User> findAllUsers(boolean loadAllData) {
        Session currentSession = getSessionFactory().getCurrentSession();
        Query fromUser = currentSession.createQuery("FROM User");
        List<User> users = fromUser.list();
        if (loadAllData) {
            users.forEach(User::getRoles);
        }
        return users;
    }

}
