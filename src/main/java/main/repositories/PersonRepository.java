package main.repositories;

import main.entities.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class PersonRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Person person) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(person);
    }
}
