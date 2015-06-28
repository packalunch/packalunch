package com.main.plugins.date;

import com.main.model.product.Meal;

import java.util.Date;

/**
 * PackALunch
 * Created by sadra on 11/5/14.
 */
public class Day {

    private boolean availability;
    private Meal meal = null;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public boolean isAvailabe() {
        return availability;
    }

    public void setAvailabe(boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Day{" +
                "date=" + date +
                ", meal=" + meal +
                ", availability=" + availability +
                '}';
    }
}
