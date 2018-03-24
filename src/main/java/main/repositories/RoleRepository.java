package main.repositories;

import main.entities.Role;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class RoleRepository extends MainRepository{

    public RoleRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Role findByRole(String role) {
        Session currentSession = getSessionFactory().getCurrentSession();
        Criteria criteria = currentSession.createCriteria(Role.class);
        criteria.add(Restrictions.eq("role", role));
        return (Role) criteria.uniqueResult();
    }
}
