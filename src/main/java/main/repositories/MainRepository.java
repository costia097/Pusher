package main.repositories;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MainRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public MainRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
