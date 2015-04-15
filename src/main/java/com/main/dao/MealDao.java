package com.main.dao;

import com.main.model.Customer;
import com.main.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * sadra
 * Created by sadra on 11/1/14.
 */
public interface MealDao extends JpaRepository<Meal, Integer> {

//    "FROM Meal WHERE customer_id = :customerId AND date between :startDate and :endDate"
//    @Query("SELECT m FROM Meal m WHERE m.customer = :customer AND m.date BETWEEN :startDate AND :endDate")
//    List <Meal> findByCustomerAndDateBetween(   Customer customer,
//                                                    Date  startDate,
//                                                    Date endDate); //todo

    List <Meal> findByCustomer(Customer customer);

    List<Meal> findByDateBetween(Date startDate, Date endDate);
}
