package com.curry.fw.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * CurryWithAri
 * Created by sadra on 11/15/14.
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @SuppressWarnings("unused")
    @Version
    @Column(name = "version")
    private Integer version;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }
}
