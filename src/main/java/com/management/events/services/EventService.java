package com.management.events.services;

import com.management.events.exceptions.InputException;
import com.management.events.models.formdata.EventFilter;
import com.management.events.utils.Util;
import com.management.events.models.City;
import com.management.events.models.Event;
import com.management.events.models.Type;
import com.management.events.models.formdata.EventFormData;
import com.spring.hibernate.dao.HibernateDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    HibernateDao dao;
    EventFormData formData;

    public EventService(HibernateDao dao) {
        this.dao = dao;
    }

    public EventFormData fetchFormData () {
        if (formData == null) {
            try (Session session = dao.getSessionFactory().openSession()) {
                formData = new EventFormData();
                formData.setCityList(dao.findAll(session, City.class));
                formData.setTypes(dao.findAll(session, Type.class));
            }
        }
        return formData;
    }

    private Criteria findAll(Session session, EventFilter filter) {
        Criterion[] conditions = filter.getConditions();
        Order order = filter.getOrder();
        int first = filter.getPage() * 4;
        Criteria criteria = session.createCriteria(Event.class);;
        for (Criterion condition : conditions) {
            criteria.add(condition);
        }
        criteria.addOrder(order);
        criteria.setFirstResult(first);
        criteria.setMaxResults(4);
        return criteria;
    }

    public List<Event> getValidatedEvents(EventFilter filter) {
        try (Session session = dao.getSessionFactory().openSession()) {
            Criteria criteria = findAll(session, filter);
            criteria.add(Restrictions.gt("status", 0));
            return criteria.list();
        }
    }

    public List<Event> getPendingEvents(EventFilter filter) {
        try (Session session = dao.getSessionFactory().openSession()) {
            Criteria criteria = findAll(session, filter);
            criteria.add(Restrictions.eq("status", 0));
            return criteria.list();
        }
    }

    // get pending events by author
    public List<Event> getPendingEventsByAuthor(EventFilter filter, int authorId) {
        try (Session session = dao.getSessionFactory().openSession()) {
            Criteria criteria = findAll(session, filter);
            criteria.add(Restrictions.eq("status", 0));
            criteria.add(Restrictions.eq("author.id", authorId));
            return criteria.list();
        }
    }


    public List<Event> findAll (EventFilter filter) {
        try (Session session = dao.getSessionFactory().openSession()) {
            return findAll(session, filter).list();
        }
    }

    public Event create (Event event) throws Exception {
        if (event.getType().getId() == 2) {
            event.setEndDate(null);
        }
        else {
            if (!event.getEndDate().after(event.getStartDate())) {
                throw new InputException("verifiez vos dates");
            }
        }
        event.setImage(Util.saveImage(event.getImage()));
        return dao.save(event);
    }

}
