package com.curry.model.dto;

import java.sql.Timestamp;
import java.util.Date;

/**
 * CurryWithAri
 * Created by sadra on 12/25/14.
 */
public class MealDayDto {

    private Date date;
    private Integer quantity;
    private Integer rating;
    private Timestamp ordered_at;
    private boolean available;

    public Date getDate() {
        return date;
    }

    public MealDayDto setDate(Date date) {
        this.date = date;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public MealDayDto setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public Integer getRating() {
        return rating;
    }

    public MealDayDto setRating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public Timestamp getOrdered_at() {
        return ordered_at;
    }

    public MealDayDto setOrdered_at(Timestamp ordered_at) {
        this.ordered_at = ordered_at;
        return this;
    }

    public boolean isAvailable() {
        return available;
    }

    public MealDayDto setAvailable(boolean available) {
        this.available = available;
        return this;
    }

    @Override
    public String toString() {
        return "MealDayDto{" +
                "date=" + date +
                ", quantity=" + quantity +
                ", rating=" + rating +
                ", ordered_at=" + ordered_at +
                ", available=" + available +
                '}';
    }
}
