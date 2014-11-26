package com.curry.plugins.date;

import com.curry.model.Meal;

import java.util.Calendar;

/**
 * CurryWithAri
 * Created by sadra on 11/5/14.
 */
public class Day extends Calendar {


    private boolean avaiability;
    private Meal meal = null;

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public boolean isAvaiability() {
        return avaiability;
    }

    public void setAvaiability(boolean avaiability) {
        this.avaiability = avaiability;
    }



    @Override
    protected void computeTime() {

    }

    @Override
    protected void computeFields() {

    }

    @Override
    public void add(int field, int amount) {

    }

    @Override
    public void roll(int field, boolean up) {

    }

    @Override
    public int getMinimum(int field) {
        return 0;
    }

    @Override
    public int getMaximum(int field) {
        return 0;
    }

    @Override
    public int getGreatestMinimum(int field) {
        return 0;
    }

    @Override
    public int getLeastMaximum(int field) {
        return 0;
    }
}
