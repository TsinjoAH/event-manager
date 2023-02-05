package com.management.events.services;

import com.management.events.models.Admin;
import com.spring.hibernate.dao.HibernateDao;
import org.springframework.stereotype.Service;

@Service
public class AdminService extends LoginService<Admin> {

    public AdminService(HibernateDao dao) {
        super(dao);
    }

}
