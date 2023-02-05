package com.management.events.models;

import com.management.events.models.common.HasName;
import com.management.events.models.common.LoginEntity;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Author extends HasName implements LoginEntity {

    @Column
    private String email;

    @Column
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = DigestUtils.sha1Hex(password);
    }
}
