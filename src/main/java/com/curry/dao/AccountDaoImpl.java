package com.curry.dao;

import com.curry.fw.dao.AbstractDao;
import com.curry.model.Account;
import org.springframework.stereotype.Repository;

/**
 * CurryWithAri
 * Created by sadra on 12/21/14.
 */
@Repository (value = "accountDao")
public class AccountDaoImpl extends AbstractDao <Account>
        implements AccountDao {

    public AccountDaoImpl () {
        super(Account.class);
    }

}
