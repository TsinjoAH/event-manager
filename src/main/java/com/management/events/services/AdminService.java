package com.management.events.services;

import com.management.events.exceptions.InputException;
import com.management.events.models.Admin;
import com.spring.hibernate.dao.HibernateDao;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AdminService {

    HibernateDao dao;

    public AdminService(HibernateDao dao) {
        this.dao = dao;
    }

    public Admin login (Admin admin) throws InputException {
        List<Admin> list = dao.findBy(Admin.class, Restrictions.eq("email", admin.getEmail()));
        if (list.isEmpty()) {
            throw new InputException("user not found");
        }
        Admin found = list.get(0);
        if (Objects.equals(admin.getPassword(), found.getPassword())) return found;
        throw new InputException("wrong password");
    }
}
