package com.main.dao;

import com.main.model.user.User;
import com.main.model.product.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * sadra
 * Created by sadra on 11/1/14.
 */
public interface MealDao extends JpaRepository<Meal, Integer> {

    List <Meal> findByUser(User user);
    List <Meal> findByDateBetween(Date startDate, Date endDate);
}
