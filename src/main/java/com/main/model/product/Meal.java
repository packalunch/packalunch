package com.main.model.product;


import com.main.fw.model.AbstractEntity;
import com.main.model.user.User;
import org.springframework.stereotype.Component;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

/**
 * PackALunch
 * Created by sadra on 11/1/14.
 */
@Entity
@Component
@Table(name="meal")
public class Meal extends AbstractEntity {

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    private Date date;
    private Integer quantity;
    private Integer rating;
    private Timestamp ordered_at;

    public User getUser() {
        return user;
    }

    public Meal setUser(User user) {
        this.user = user;
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

    public Integer getRating() {
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
                " date=" + date +
                ", quantity=" + quantity +
                ", rating=" + rating +
                ", ordered_at=" + ordered_at +
                '}';
    }
}
