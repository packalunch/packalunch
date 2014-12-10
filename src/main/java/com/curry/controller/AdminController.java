package com.curry.controller;

import com.curry.dao.CustomerDao;
import com.curry.model.Customer;
import com.curry.model.dto.DinnerDto;
import com.curry.plugins.date.Day;
import com.curry.plugins.date.Week;
import com.curry.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * CurryWithAri
 * Created by sadra on 9/18/14.
 */
@Controller
public class AdminController {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CustomerService customerService;

	@RequestMapping(value="/")
	public ModelAndView test() throws IOException{
		return new ModelAndView("home");
	}

    @RequestMapping("admin/list")
    @Transactional
    public String index(Model model) {

        List<Customer> customerList = customerDao.list();
        List<DinnerDto> dinnersList = new ArrayList<DinnerDto>();

        //todo: get it from form
        Calendar now = Calendar.getInstance();
        int weekNumber = now.get(Calendar.WEEK_OF_YEAR);

        Week week = new Week(50);

        for (Customer customer : customerList) {
            DinnerDto dinner = new DinnerDto();

            dinner.setId(customer.getId());
            dinner.setFirstName(customer.getFirst_name());
            dinner.setLastName(customer.getLast_name());

            //todo: get from service
            List<Day> dinerSchedule = customerService.getDinerSchedule(customer, week);

            for (Day day : dinerSchedule) {
                System.out.println("CUSTOMER-MEAL::" + day.getMeal());
            }

            dinner.setDinerSchedule(dinerSchedule);

            dinnersList.add(dinner);
        }

        for (DinnerDto dinner : dinnersList)
            System.out.println("CUSTOMER::" + dinner.getDinerSchedule());

        model.addAttribute("users", dinnersList);

        System.out.print("HEREEEEEEEEEEEEE");

        return "admin";
    }
}
