package com.main.service;

import com.main.model.user.User;
import com.main.model.dto.MealDayDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PackALunch
 * Created by sadra on 11/2/14.
 */
@Service
public interface MealService {
    public boolean saveMeals (User user, List<MealDayDto> mealDayDtoList) throws Exception;
}
