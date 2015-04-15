package com.main.dao;

import com.main.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * sadra
 * Created by sadra on 11/1/14.
 */
@Repository
public interface AccountDao extends JpaRepository<Account, Integer> {

}
