package com.curry.model;


import com.curry.fw.model.AbstractEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * CurryWithAri
 * Created by sadra on 11/1/14.
 */
@Entity
@Table(name="meal")
public class Meal extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name="customer_id", insertable=false, updatable=false)
    private Customer customer;

    private Date date;
    private int quantity;
    private Integer rating;
    private Timestamp ordered_at;


    public Customer getCustomer() {
        return customer;
    }

    public Meal setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Meal setDate(Date date) {
        this.date = date;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public Meal setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public int getRating() {
        return rating;
    }

    public Meal setRating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public Timestamp getOrdered_at() {
        return ordered_at;
    }

    public Meal setOrdered_at(Timestamp ordered_at) {
        this.ordered_at = ordered_at;
        return this;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", date=" + date +
                ", quantity=" + quantity +
                ", rating=" + rating +
                ", ordered_at=" + ordered_at +
                '}';
    }
}
