package com.curry.dao;

import com.curry.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CurryWithAri
 * Created by sadra on 11/1/14.
 */
@Repository
public interface AccountDao extends JpaRepository<Account, Integer> {

}
