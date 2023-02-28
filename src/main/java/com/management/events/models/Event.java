package com.management.events.models;


import com.management.events.models.common.HasId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Event extends HasId {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(name = "start_date", nullable = false)
    private Timestamp startDate;

    @Column(name = "end_date")
    private Timestamp endDate;

    @Column(name = "creation_date", nullable = false, insertable = false, updatable = false)
    private Timestamp creationDate = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "published_date")
    private Timestamp publishedDate;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private Integer status = 0;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;


    @Column(name = "home_status", nullable = false)
    private Integer homeStatus = 0;


    // is validated method
    public boolean isValidated() {
        return status > 0;
    }
}
