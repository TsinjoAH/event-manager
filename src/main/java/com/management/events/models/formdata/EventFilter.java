package com.management.events.models.formdata;

import com.management.events.models.Type;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class EventFilter {

    private Integer type;
    private Boolean desc = true;
    private Boolean orderedByStart = true;
    private String keyword;
    private Integer page = 0;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getDesc() {
        return desc;
    }

    public void setDesc(Boolean desc) {
        this.desc = desc;
    }

    public Boolean getOrderedByStart() {
        return orderedByStart;
    }

    public void setOrderedByStart(Boolean orderedByStart) {
        this.orderedByStart = orderedByStart;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Criterion[] getConditions() {
        List<Criterion> conditions = new ArrayList<>();
        if (type != null && type != 0) {
            Type t = new Type();
            t.setId(type);
            conditions.add(Restrictions.eq("type", t));
        }
        if (keyword != null) {
            conditions.add(Restrictions.or(
                Restrictions.ilike("title", "%" + keyword + "%"),
                Restrictions.ilike("description", "%" + keyword + "%"))
            );
        }
        return conditions.toArray(new Criterion[0]);
    }

    public Order getOrder() {
        if (orderedByStart) {
            return desc ? Order.desc("startDate") : Order.asc("startDate");
        } else {
            return desc ? Order.desc("endDate") : Order.asc("endDate");
        }
    }

}
