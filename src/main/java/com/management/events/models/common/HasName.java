package com.management.events.models.common;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class HasName extends HasId{
    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
