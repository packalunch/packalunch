package com.curry.service;

import org.springframework.stereotype.Service;

/**
 * CurryWithAri
 * Created by sadra on 11/2/14.
 */
@Service
public class MealServiceImpl implements MealService {

//    @Autowired
//    private DatePlugin datePlugin;
//    @Autowired
//    private CustomerDao customerDao;
//    @Autowired
//    private MealDao mealDao;

//    public getWeekMeal (int weekNumber) {
//
//        List<Meal> meals = getMeals(weekNumber);
//        HashMap<Integer,Customer> customers = new HashMap<Integer, Customer>();
//
//        HashMap<Customer, List>
//
//        for (Meal meal : meals) {
//            if (!customers.containsKey(meal.getCustomer_id())) {
//                Customer customer = customerDao.findById(meal.getCustomer_id());
//                customers.put(customer.getId(), customer);
//            }
//        }
//
//
//    }
//
//    private List<Meal> getMeals(int weekNumber) {
//        Week week = new Week(weekNumber);
//        String startDate = datePlugin.toString(week.getWeekStart());
//        String endDate = datePlugin.toString(week.getWeekEnd());
//
//        return mealDao.listMealFilterByRange(startDate, endDate);
//    }
}
