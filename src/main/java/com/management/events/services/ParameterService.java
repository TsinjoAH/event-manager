package com.management.events.services;

import com.management.events.exceptions.InputException;
import com.management.events.models.Params;
import com.spring.hibernate.dao.HibernateDao;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParameterService {

    @Autowired
    HibernateDao dao;

    public Integer getHomePageSize () {
        try (Session session = dao.getSessionFactory().openSession()) {
            return getHomePageSize(session);
        }
    }

    public Integer getHomePageSize(Session session) {
        Query<Integer> query = session.createNativeQuery("SELECT value FROM params WHERE id = 'home_page'");
        return query.getSingleResult();
    }

    public Integer setHomePageSize(Integer size) throws Exception {
        try (Session session = dao.getSessionFactory().openSession()) {
            Query<Params> homePage = session.createQuery("FROM Params WHERE id = 'home_page'");
            Params param = homePage.getSingleResult();
            param.setValue(size);
            dao.save(session, param);
        }
        return size;
    }

}
