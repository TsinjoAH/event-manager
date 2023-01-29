package com.management.events.services;

import com.management.events.exceptions.InputException;
import com.management.events.utils.Util;
import com.management.events.models.City;
import com.management.events.models.Event;
import com.management.events.models.Type;
import com.management.events.models.formdata.EventFormData;
import com.spring.hibernate.dao.HibernateDao;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    public List<Event> findAll () {
        return dao.findAll(Event.class);
    }

    public Event create (Event event) throws Exception {
        if (event.getType().getId() == 2) {
            event.setEndDate(null);
        }
        event.setImage(Util.saveImage(event.getImage()));
        return dao.save(event);
    }

}
