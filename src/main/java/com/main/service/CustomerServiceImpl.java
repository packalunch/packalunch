package com.main.service;

import com.main.dao.AccountDao;
import com.main.dao.UserDao;
import com.main.dao.MealDao;
import com.main.model.billing.Account;
import com.main.model.user.Credential;
import com.main.model.user.User;
import com.main.model.product.Meal;
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
    private UserDao userDao;
    @Autowired
    private MealDao mealDao;
    @Autowired
    private AccountDao accountDao;


    @Override
    public UserDto findCustomerById(int id) {
        User user = userDao.findOne(id);
        if (null == user)
            return null;

        return getDinerDto(user);
    }

    @Override
    public UserDto findByUsername(String userName) {
        User user = userDao.findByEmail(userName);
        if (null == user)
            return null;

        return getDinerDto(user);
    }

    @Override
    public User getCustomerById(int id) {
        return userDao.findOne(id);
    }

    @Override
    public List<UserDto> findCustomers() {

        List <User> userList =  userDao.findAll();
        List <UserDto> userDtoList = new ArrayList<UserDto>();

        for (User user : userList){
            UserDto userDto = getCustomerDto(user);
            userDtoList.add(userDto);
        }

        return userDtoList;

    }

    @Override
    public User saveCustomer(UserDto userDto) {
        User user = getNewCustomer(userDto);
        userDao.save(user);
        return user;
    }


    @Override
    public User updateCustomer (UserDto userDto) {
        User user = userDao.findOne(userDto.getId());
        user.setFirst_name(userDto.getFirstName())
                .setLast_name(userDto.getLastName())
                .setAddress(userDto.getAddress())
                .setTelephone(userDto.getTelephone());
        userDao.save(user);
        return user;
    }

    @Override
    public void deleteCustomer(int id) {
        User user = userDao.findOne(id);
        userDao.delete(user);
    }

    @Override
    public List<DinerDto> getDiners(Week week) {

        List<DinerDto> dinersList = new ArrayList<>();

        Date startDate = week.getDate(Calendar.SUNDAY).getTime();
        Date endDate   = week.getDate(Calendar.SATURDAY).getTime();

        List<Meal> mealList = mealDao.findByDateBetween(startDate, endDate);

        List<User> userList = getUniqueCustomers(mealList);

        for (User user : userList) {
            DinerDto diner = getDinerMealsDto(user, mealList, week);
            dinersList.add(diner);
        }

        return dinersList;
    }


    @Override
    public DinerDto getDiner(int id, Week week) {
        Date startDate = week.getDate(Calendar.SUNDAY).getTime();
        Date endDate   = week.getDate(Calendar.SATURDAY).getTime();
        User user = userDao.findOne(id);
        System.out.println("IN SERVICE" + user.getId());
        List<Meal> mealList =  mealDao.findByUser(user);
        System.out.println("IN SERVICE ---- after jpa" + mealList.size());

        DinerDto diner = getDinerMealsDto(user, mealList, week);
        return diner;
    }

    private DinerDto getDinerMealsDto(User user, List<Meal> mealList, Week week) {
        DinerDto dinerDto = getDinerDto(user);
        List<MealDayDto> mealDayDtoList = getDaySchedule(week, mealList);
        dinerDto.setDinerSchedule(mealDayDtoList);

        return dinerDto;
    }

    /**
     * @param mealList
     * @return
     */
    private List<User> getUniqueCustomers(List<Meal> mealList) {
        List<User> customersList = new ArrayList<>();
        for (Meal meal : mealList){
            if (!customersList.contains(meal.getUser())){
                customersList.add(meal.getUser());
            }
        }
        return customersList;
    }

    @Override
    public void savePayment(DinerDto dinerDto) {
        User user = userDao.findOne(dinerDto.getId());

        if (null != user && null != user.getAccount()) {
            Account account = user.getAccount();
            account.setPayment_amount(dinerDto.getAccountDto().getPayment_amount())
                    .setAccount_due( account.getAccount_due() - dinerDto.getAccountDto().getPayment_amount() )
                    .setPayment_date(new Date());

            accountDao.save(account);
        } else {
            //todo: throw exception
        }

    }


    private DinerDto getDinerDto(User user) {
        DinerDto diner = new DinerDto();

        diner.setId(user.getId());
        diner.setFirstName(user.getFirst_name())
                .setLastName(user.getLast_name())
                .setAddress(user.getAddress())
                .setTelephone(user.getTelephone());

        if (null != user.getAccount()) {
            AccountDto accountDto = getAccountDto(user);
            diner.setAccountDto(accountDto);
        }
        return diner;
    }

    private AccountDto getAccountDto(User user) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(user.getAccount().getId())
                .setPayment_amount(user.getAccount().getPayment_amount())
                .setAccount_due(user.getAccount().getAccount_due())
                .setPayment_date(user.getAccount().getPayment_date());
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

    private UserDto getCustomerDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId())
                .setFirstName(user.getFirst_name())
                .setLastName(user.getLast_name())
                .setEmail(user.getEmail())
                .setAddress(user.getAddress())
                .setTelephone(user.getTelephone());
        return userDto;
    }

    private User getNewCustomer(UserDto userDto) {
        User user = new User();
        user.setFirst_name(userDto.getFirstName())
                .setLast_name(userDto.getLastName())
                .setAddress(userDto.getAddress())
                .setEmail(userDto.getEmail())
                .setTelephone(userDto.getTelephone());

        Account account = new Account();
        account.setUser(user);
        user.setAccount(account);

        Credential credential = new Credential();
        credential
                .setRole( Role.ROLE_USER)
                .setSignInProvider(SocialMediaService.FACEBOOK)
                .setUser(user)
                .setPassword("").setSalt("");

        user.setCredential(credential);

        return user;
    }


}
