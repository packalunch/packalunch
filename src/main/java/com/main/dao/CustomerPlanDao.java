package com.main.dao;

import com.main.model.product.Plan;
import com.main.model.user.Customer;
import com.main.model.user.CustomerPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * sadra
 * Created by sadra on 11/1/14.
 */
@Repository
public interface CustomerPlanDao extends JpaRepository<CustomerPlan, Integer> {

    Set <CustomerPlan> findByUser (Customer customer);
    Set <CustomerPlan> findByPlan (Plan plan);

}
