package com.curry.dao;

import com.curry.model.Customer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CurryWithAri
 * Created by sadra on 10/30/14.
 */
public interface CustomerDao {
    public void save (Customer customer);
    public List <Customer> list();
    public Customer findById(int id);
}
