package com.curry.dao;

import com.curry.model.Customer;
import com.curry.model.Meal;
import org.jboss.logging.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * CurryWithAri
 * Created by sadra on 11/1/14.
 */
public interface MealDao extends JpaRepository<Meal, Integer> {

//    "FROM Meal WHERE customer_id = :customerId AND date between :startDate and :endDate"
    @Query("SELECT m FROM Meal m WHERE m.customer = :customer AND m.date BETWEEN :startDate AND :endDate")
    public List <Meal> findByCustomerAndDateBetween(   Customer customer,
                                                    Date  startDate,
                                                    Date endDate); //todo


    List<Meal> findByDateBetween(Date startDate, Date endDate);
}
