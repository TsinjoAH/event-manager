package com.management.events.models;

import com.management.events.exceptions.InputException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Params {

    @Id
    @Column(name = "id")
    private String name;

    @Column
    private Integer value;

    public void setValue(Integer value) throws InputException {
        if (value < 0 ) throw new InputException("invalid value");
        this.value = value;
    }
}
