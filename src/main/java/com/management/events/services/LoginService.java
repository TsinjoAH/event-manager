package com.management.events.services;

import com.management.events.exceptions.InputException;
import com.management.events.models.common.LoginEntity;
import com.spring.hibernate.dao.HibernateDao;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Objects;

public class LoginService <T extends LoginEntity> {

    protected HibernateDao dao;

    public LoginService(HibernateDao dao) {
        this.dao = dao;
    }

    public T login (T user) throws InputException {
        List<T> list = (List<T>) dao.findBy(user.getClass(), Restrictions.eq("email", user.getEmail()));
        if (list.isEmpty()) {
            throw new InputException("user not found");
        }
        T found = list.get(0);
        if (Objects.equals(user.getPassword(), found.getPassword())) return found;
        throw new InputException("wrong password");
    }

}
