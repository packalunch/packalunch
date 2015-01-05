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

    private String month;
    private String day;
    private String dayNumber;
    private boolean selected;


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


    public String getMonth() {
        return month;
    }

    public MealDayDto setMonth(String month) {
        this.month = month;
        return this;
    }

    public String getDay() {
        return day;
    }

    public MealDayDto setDay(String day) {
        this.day = day;
        return this;
    }

    public String getDayNumber() {
        return dayNumber;
    }

    public MealDayDto setDayNumber(String dayNumber) {
        this.dayNumber = dayNumber;
        return this;
    }

    public boolean isSelected() {
        return selected;
    }

    public MealDayDto setSelected(boolean selected) {
        this.selected = selected;
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
