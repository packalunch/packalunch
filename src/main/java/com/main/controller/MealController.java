package com.main.controller;

import com.main.model.user.User;
import com.main.model.dto.DinerDto;
import com.main.model.dto.MealDayDto;
import com.main.service.CustomerService;
import com.main.service.MealService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.List;

/**
 * sadra
 * Created by sadra on 12/10/14.
 */
@Controller
public class MealController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private MealService mealService;

    static Logger log = Logger.getLogger(MealController.class);

    @Transactional
    @RequestMapping(value = "api/dinerMeal/{id}", method = RequestMethod.POST)
    public @ResponseBody
    boolean saveDinerMeal (@RequestBody DinerDto dinerDto) throws Exception {

        User user = customerService.getCustomerById(dinerDto.getId());

        if (user == null){
            return false;
        } else {
            log.info("Controller meal:::::::" + user);
            List <MealDayDto> mealDayDtoList = dinerDto.getDinerSchedule();
            log.info("Controller meal:::::::" + mealDayDtoList);

            mealService.saveMeals(user, mealDayDtoList);

            return true;
        }

    }

    //todo: Handle exception

}
