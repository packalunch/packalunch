package com.curry.dao;

import com.curry.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CurryWithAri
 * Created by sadra on 10/30/14.
 */
@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {

    public Customer findByEmail(String email);
}
