package com.main.fw.model;

import javax.persistence.*;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Version
    private Integer version = 0;

    public Integer getVersion() {
        return version;
    }
}
