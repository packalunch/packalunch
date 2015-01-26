package com.curry.dao;

import com.curry.model.Meal;
import java.util.Date;
import java.util.List;

/**
 * CurryWithAri
 * Created by sadra on 11/1/14.
 */
public interface MealDao {
    public void save (Meal meal);
    public void save (List<Meal> meal);
    public List <Meal> listByCustomerByRange(int id, Date startDate, Date endDate);
}
