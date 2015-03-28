package com.curry.controller;

import com.curry.model.dto.CustomerDto;
import com.curry.model.dto.DinerDto;
import com.curry.plugins.date.Week;
import com.curry.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;

/**
 * CurryWithAri
 * Created by sadra on 9/18/14.
 */
@Controller
public class AdminController {

    @Autowired
    private CustomerService customerService;

    @Transactional
    @RequestMapping(value = "api/diner", method = RequestMethod.GET)
    public @ResponseBody
    List <DinerDto> getDiners () {

        //todo: get it from form
        Calendar now = Calendar.getInstance();
        int weekNumber = now.get(Calendar.WEEK_OF_YEAR);

        weekNumber = 2;
        Week week = new Week (weekNumber);
        return customerService.getDiners(week);
    }

    @Transactional
    @RequestMapping (value = "api/diner/{id}/payment", method = RequestMethod.POST)
    public @ResponseBody
    int addDinerPayment (@RequestBody DinerDto dinerDto) {
        customerService.savePayment(dinerDto);
        return 1;
    }


}
