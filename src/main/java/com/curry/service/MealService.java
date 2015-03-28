package com.curry.service;

import com.curry.model.Customer;
import com.curry.model.Meal;
import com.curry.model.dto.MealDayDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CurryWithAri
 * Created by sadra on 11/2/14.
 */
@Service
public interface MealService {
    public boolean saveMeals (Customer customer, List<MealDayDto> mealDayDtoList) throws Exception;
}
