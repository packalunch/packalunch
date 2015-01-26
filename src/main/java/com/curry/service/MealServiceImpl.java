package com.curry.service;

import com.curry.dao.MealDao;
import com.curry.model.Customer;
import com.curry.model.Meal;
import com.curry.model.dto.MealDayDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * CurryWithAri
 * Created by sadra on 11/2/14.
 */
@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealDao mealDao;

    @Override
    public boolean saveMeals(Customer customer, List<MealDayDto> mealDayDtoList) throws Exception {
        List <Meal> mealList = new ArrayList<Meal>();
        java.util.Date date= new java.util.Date();

        for (MealDayDto mealDayDto : mealDayDtoList){
            Meal meal = new Meal();
            meal.setCustomer(customer)
                    .setQuantity(mealDayDto.getQuantity())
                    .setDate(mealDayDto.getDate())
                    .setOrdered_at(new Timestamp(date.getTime()));
            mealList.add(meal);
        }
        try {
            mealDao.save(mealList);
            return true;
        } catch (Exception e) {
//            throw  e;
            return false;
        }
    }
}
