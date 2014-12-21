package com.curry.service;

import com.curry.dao.CustomerDao;
import com.curry.dao.MealDao;
import com.curry.model.Customer;
import com.curry.model.Meal;
import com.curry.model.dto.CustomerDto;
import com.curry.plugins.date.DatePlugin;
import com.curry.plugins.date.Day;
import com.curry.plugins.date.Week;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * CurryWithAri
 * Created by sadra on 11/2/14.
 */
@Service (value = "customerService")
public class CustomerServiceImpl implements CustomerService {

    static Logger log = Logger.getLogger(CustomerService.class);
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private MealDao mealDao;


    @Override
    public CustomerDto findCustomerById(int id) {
        Customer customer = customerDao.findById(id);
        if (null == customer)
            return null; // todo: throw exception

        return getCustomerDto(customer);
    }



    @Override
    public List<CustomerDto> findCustomers() {

        List <Customer> customerList =  customerDao.list();
        List <CustomerDto> customerDtoList = new ArrayList<CustomerDto>();

        for (Customer customer : customerList){
            CustomerDto customerDto = getCustomerDto(customer);
            customerDtoList.add(customerDto);
        }

        return customerDtoList;

    }

    @Override
    public Customer saveCustomer(CustomerDto customerDto) {
        Customer customer = getCustomer(customerDto);
        customerDao.save(customer); // todo: try catch
        return customer;
    }

    public Customer updateCustomer (CustomerDto customerDto) {
        Customer customer = customerDao.findById(customerDto.getId());
        customer.setFirst_name(customerDto.getFirst_name())
                .setLast_name(customerDto.getLast_name())
                .setAddress(customerDto.getAddress())
                .setTelephone(customerDto.getTelephone());
        customerDao.update(customer); // todo: try catch
        return customer;
    }

    @Override
    public void deleteCustomer(int id) {
        Customer customer = customerDao.findById(id);
        customerDao.delete (customer);
    }

    private Customer getCustomer (CustomerDto customerDto) {
        Customer customer = new Customer ();
        customer.setFirst_name(customerDto.getFirst_name())
                .setLast_name(customerDto.getLast_name())
                .setAddress(customerDto.getAddress())
                .setTelephone(customerDto.getTelephone());
        return customer;
    }

    private CustomerDto getCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId())
                .setFirst_name(customer.getFirst_name())
                .setLast_name(customer.getLast_name())
                .setAddress(customer.getAddress())
                .setTelephone(customer.getTelephone());
        return customerDto;
    }

    /**
     * always return 7 day list.
     * @param customer
     * @param week
     * @return
     */
    @Override
    public List <Day> getDinerSchedule(Customer customer, Week week) {

        Date startDate = week.getDate(Calendar.SUNDAY).getTime();
        Date endDate   = week.getDate(Calendar.SATURDAY).getTime();

        log.info("startDate:"+ startDate);
        log.info("endDate:"+ endDate);
        List <Meal> meals = mealDao.listByCustomerByRange(customer.getId(), startDate, endDate);

        List<Day> weekSchedule = getDaySchedule(week, meals);

        return weekSchedule;
    }

    private List<Day> getDaySchedule(Week week, List<Meal> meals) {
        List <Day> weekSchedule = week.getWeekList();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Day day : weekSchedule) {
            for (Meal meal : meals) {
                //todo refactor if possible
                if (sdf.format(meal.getDate())
                        .equalsIgnoreCase(sdf.format(day.getDate()))) {
                    day.setMeal(meal);
                    day.setAvailabe(true);
                    log.info("meal::: " + meal);
                }
            }
            log.info("DAY:: " + day.toString());
        }
        return weekSchedule;
    }
}
