package com.management.events.services;

import com.management.events.models.Author;
import com.spring.hibernate.dao.HibernateDao;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends LoginService<Author> {

    public AuthorService(HibernateDao dao) {
        super(dao);
    }

}
