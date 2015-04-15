package com.main.service;

import com.main.dao.AccountDao;
import com.main.dao.CustomerDao;
import com.main.dao.MealDao;
import com.main.model.Account;
import com.main.model.Customer;
import com.main.model.Meal;
import com.main.model.dto.*;
import com.main.plugins.date.Day;
import com.main.plugins.date.Week;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * PackALunch
 * Created by sadra on 11/2/14.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    static Logger log = Logger.getLogger(CustomerService.class);
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private MealDao mealDao;
    @Autowired
    private AccountDao accountDao;


    @Override
    public CustomerDto findCustomerById(int id) {
        Customer customer = customerDao.findOne(id);
        if (null == customer)
            return null;

        return getDinerDto(customer);
    }

    @Override
    public CustomerDto findByUsername(String userName) {
        Customer customer = customerDao.findByEmail(userName);
        if (null == customer)
            return null;

        return getDinerDto(customer);
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerDao.findOne(id);
    }

    @Override
    public List<CustomerDto> findCustomers() {

        List <Customer> customerList =  customerDao.findAll();
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

    @Override
    public Customer updateCustomer (CustomerDto customerDto) {
        Customer customer = customerDao.findOne(customerDto.getId());
        customer.setFirst_name(customerDto.getFirst_name())
                .setLast_name(customerDto.getLast_name())
                .setAddress(customerDto.getAddress())
                .setTelephone(customerDto.getTelephone());
        customerDao.save(customer); // todo: try catch
        return customer;
    }

    @Override
    public void deleteCustomer(int id) {
        Customer customer = customerDao.findOne(id);
        customerDao.delete(customer);
    }

    @Override
    public List<DinerDto> getDiners(Week week) {

        List<DinerDto> dinersList = new ArrayList<>();

        Date startDate = week.getDate(Calendar.SUNDAY).getTime();
        Date endDate   = week.getDate(Calendar.SATURDAY).getTime();

        List<Meal> mealList = mealDao.findByDateBetween(startDate, endDate);

        List<Customer> customerList = getUniqueCustomers(mealList);

        for (Customer customer : customerList) {
            DinerDto diner = getDinerMealsDto(customer, mealList, week);
            dinersList.add(diner);
        }

        return dinersList;
    }


    @Override
    public DinerDto getDiner(int id, Week week) {
        Date startDate = week.getDate(Calendar.SUNDAY).getTime();
        Date endDate   = week.getDate(Calendar.SATURDAY).getTime();
        Customer customer = customerDao.findOne(id);
        System.out.println("IN SERVICE" + customer.getId());
        List<Meal> mealList =  mealDao.findByCustomer(customer);
        System.out.println("IN SERVICE ---- after jpa" + mealList.size());

        DinerDto diner = getDinerMealsDto(customer, mealList, week);
        return diner;
    }

    private DinerDto getDinerMealsDto(Customer customer, List<Meal> mealList, Week week) {
        DinerDto dinerDto = getDinerDto (customer);
        List<MealDayDto> mealDayDtoList = getDaySchedule(week, mealList);
        dinerDto.setDinerSchedule(mealDayDtoList);

        return dinerDto;
    }

    /**
     * @param mealList
     * @return
     */
    private List<Customer> getUniqueCustomers(List<Meal> mealList) {
        List<Customer> customersList = new ArrayList<>();
        for (Meal meal : mealList){
            if (!customersList.contains(meal.getCustomer())){
                customersList.add(meal.getCustomer());
            }
        }
        return customersList;
    }

    @Override
    public void savePayment(DinerDto dinerDto) {
        Customer customer = customerDao.findOne(dinerDto.getId());

        if (null != customer && null != customer.getAccount()) {
            Account account = customer.getAccount();
            account.setPayment_amount(dinerDto.getAccountDto().getPayment_amount())
                    .setAccount_due( account.getAccount_due() - dinerDto.getAccountDto().getPayment_amount() )
                    .setPayment_date(new Date());

            accountDao.save(account);
        } else {
            //todo: throw exception
        }

    }


    private DinerDto getDinerDto(Customer customer) {
        DinerDto diner = new DinerDto();

        diner.setId(customer.getId());
        diner.setFirst_name(customer.getFirst_name())
                .setLast_name(customer.getLast_name())
                .setAddress(customer.getAddress())
                .setTelephone(customer.getTelephone());

        if (null != customer.getAccount()) {
            AccountDto accountDto = getAccountDto(customer);
            diner.setAccountDto(accountDto);
        }
        return diner;
    }

    private AccountDto getAccountDto(Customer customer) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(customer.getAccount().getId())
                .setPayment_amount(customer.getAccount().getPayment_amount())
                .setAccount_due(customer.getAccount().getAccount_due())
                .setPayment_date(customer.getAccount().getPayment_date());
        return accountDto;
    }

//todo FIX: BROKEN
    private List<MealDayDto> getDaySchedule(Week week, List<Meal> meals) {

        List <Day> weekSchedule = week.getWeekList();

        List <MealDayDto> mealDayDtoList = new ArrayList<MealDayDto>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Day day : weekSchedule) {

            MealDayDto mealDayDto = new MealDayDto();
            for (Meal meal : meals) {
                //todo refactor if possible
                if (sdf.format(meal.getDate()).equalsIgnoreCase(sdf.format(day.getDate()))) {

                    mealDayDto.setAvailable(true)
                        .setDate(meal.getDate());
                }

            }
            mealDayDtoList.add(mealDayDto);
        }
        return mealDayDtoList;
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

    private Customer getCustomer (CustomerDto customerDto) {
        Customer customer = new Customer ();
        customer.setFirst_name(customerDto.getFirst_name())
                .setLast_name(customerDto.getLast_name())
                .setAddress(customerDto.getAddress())
                .setEmail(customerDto.getEmail())
                .setTelephone(customerDto.getTelephone());

        Account account = new Account();
        account.setCustomer(customer);
        customer.setAccount(account);
        return customer;
    }
}
