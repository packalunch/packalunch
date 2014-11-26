package com.curry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

	@RequestMapping(value="/")
	public ModelAndView test() throws IOException{
		return new ModelAndView("home");
	}

    @RequestMapping("/list")
    public String index(Model model) {
//
//        List<Customer> customerList = customerDao.list();
//        List<Dinner> dinnersList = new ArrayList<>();
//
//        //todo: get it from form
//        Calendar now = Calendar.getInstance();
//        int weekNumber = now.get(Calendar.WEEK_OF_YEAR);
//
//        Week week = new Week(weekNumber);
//
//        for (Customer customer : customerList) {
//            Dinner dinner = new Dinner();
//
//            dinner.setId(customer.getId());
//            dinner.setFirst_name(customer.getFirst_name());
//            dinner.setLast_name(customer.getLast_name());
//
//            //todo: get from service
//            List<Day> dinerSchedule = customerService.getDinerSchedule(dinner.getId(), week);
//
//            for (Day day : dinerSchedule) {
//                System.out.println("CUSTOMER-MEAL::" + day.getMeal());
//            }
//
//            dinner.setDinnerSchedule(dinerSchedule);
//
//            dinnersList.add(dinner);
//        }
//
//        for (Dinner dinner : dinnersList)
//            System.out.println("CUSTOMER::" + dinner.getDinnerSchedule());
//
//        model.addAttribute("users", dinnersList);
System.out.print("HEREEEEEEEEEEEEE");
        return "admin";
    }
}
