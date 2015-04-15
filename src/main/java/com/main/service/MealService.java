package com.main.service;

import com.main.model.Customer;
import com.main.model.dto.MealDayDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PackALunch
 * Created by sadra on 11/2/14.
 */
@Service
public interface MealService {
    public boolean saveMeals (Customer customer, List<MealDayDto> mealDayDtoList) throws Exception;
}
