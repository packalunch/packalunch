package com.main.controller;

import com.main.model.dto.DinerDto;
import com.main.plugins.date.Week;
import com.main.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;

/**
 * sadra
 * Created by sadra on 9/18/14.
 */
@Controller
public class DinerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DinerController.class);

    @Autowired
    private CustomerService customerService;

    @Transactional
    @RequestMapping(value = "api/diner", method = RequestMethod.GET)
    public @ResponseBody
    List <DinerDto> getDiners () {

        //todo: get it from form
        Calendar now = Calendar.getInstance();
        int weekNumber = now.get(Calendar.WEEK_OF_YEAR);

        Week week = new Week (weekNumber);
        return customerService.getDiners(week);
    }

    @Transactional
    @RequestMapping(value = "api/diner/{id}", method = RequestMethod.GET)
    public @ResponseBody
    DinerDto getDiner (@PathVariable("id") int id) {

        Calendar now = Calendar.getInstance();
        int weekNumber = now.get(Calendar.WEEK_OF_YEAR);
        System.out.println("HERE IN CONTROLLER ---------------" + weekNumber);
        Week week = new Week (weekNumber);
        DinerDto dinerDto = customerService.getDiner(id, week);

        System.out.println(dinerDto.toString());
        return dinerDto;

    }

    @Transactional
    @RequestMapping (value = "api/diner/{id}/payment", method = RequestMethod.POST)
    public @ResponseBody
    int addDinerPayment (@RequestBody DinerDto dinerDto) {
        customerService.savePayment(dinerDto);
        return 1;
    }


}
