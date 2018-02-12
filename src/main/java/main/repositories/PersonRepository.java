package main.repositories;

import main.entities.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PersonRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public void save(Person person) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(person);
    }
}
