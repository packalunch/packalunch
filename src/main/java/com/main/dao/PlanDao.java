package com.main.dao;

import com.main.model.product.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * sadra
 * Created by sadra on 11/1/14.
 */
@Repository
public interface PlanDao extends JpaRepository<Plan, Integer> {

}
