package com.curry.service;

import com.curry.dao.AccountDao;
import com.curry.dao.CustomerDao;
import com.curry.dao.MealDao;
import com.curry.model.Account;
import com.curry.model.Customer;
import com.curry.model.Meal;
import com.curry.model.dto.*;
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
    @Autowired
    private AccountDao accountDao;


    @Override
    public CustomerDto findCustomerById(int id) {
        Customer customer = customerDao.findById(id);
        if (null == customer)
            return null; // todo: throw exception

        return getDinerDto(customer);
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

    @Override
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

    @Override
    public List<DinerDto> getDiners(Week week) {
        List<Customer> customerList = customerDao.list();
        List<DinerDto> dinersList = new ArrayList<DinerDto>();


        for (Customer customer : customerList) {
            DinerDto diner = getDinerDto(week, customer);
            dinersList.add(diner);
        }

        return dinersList;
    }

    @Override
    public void savePayment(DinerDto dinerDto) {
        Customer customer = customerDao.findById(dinerDto.getId());

        if (null != customer && null != customer.getAccount()) {
            Account account = customer.getAccount();
            account.setPayment_amount(dinerDto.getAccountDto().getPayment_amount())
                    .setAccount_due( account.getAccount_due() - dinerDto.getAccountDto().getPayment_amount() )
                    .setPayment_date(new Date());

            accountDao.update(account);
        } else {
            //todo: throw exception
        }

    }


    private DinerDto getDinerDto(Week week, Customer customer) {
        DinerDto diner = getDinerDto(customer);

        List<MealDayDto> dinerSchedule = getDinerSchedule(customer, week);
        diner.setDinerSchedule(dinerSchedule);

        return diner;
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

    /**
     * always return 7 day list.
     * @param customer
     * @param week
     * @return
     */
    @Override
    public List<MealDayDto> getDinerSchedule(Customer customer, Week week) {

        Date startDate = week.getDate(Calendar.SUNDAY).getTime();
        Date endDate   = week.getDate(Calendar.SATURDAY).getTime();

        List <Meal> meals = mealDao.listByCustomerByRange(customer.getId(), startDate, endDate);

        List<MealDayDto> weekSchedule = getDaySchedule(week, meals);

        return weekSchedule;
    }



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
                .setTelephone(customerDto.getTelephone());

        Account account = new Account();
        account.setCustomer(customer);
        customer.setAccount(account);
        return customer;
    }
}
