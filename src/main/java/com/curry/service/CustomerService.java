package com.curry.service;

import com.curry.model.Customer;
import com.curry.plugins.date.Day;
import com.curry.plugins.date.Week;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CurryWithAri
 * Created by sadra on 11/2/14.
 */
@Service
public interface CustomerService {
    List<Day> getDinerSchedule(Customer customer, Week week);

}
