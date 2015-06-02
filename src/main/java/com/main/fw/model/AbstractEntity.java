package com.main.fw.model;

import javax.annotation.PreDestroy;
import javax.persistence.*;
import java.util.Date;

/**
 * sadra
 * Created by sadra on 11/15/14.
 */
@MappedSuperclass
public abstract class AbstractEntity {

    @SuppressWarnings("unused")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;
    private Date created;
    private Date updated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    @Version
    private Integer version = 0;

    public Integer getVersion() {
        return version;
    }
}
