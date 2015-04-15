package com.main.dao;

import com.main.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * sadra
 * Created by sadra on 10/30/14.
 */
@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {

    public Customer findByEmail(String email);
}
