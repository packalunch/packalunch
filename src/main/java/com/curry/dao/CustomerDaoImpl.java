package com.curry.dao;

import com.curry.fw.dao.AbstractDao;
import com.curry.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CurryWithAri
 * Created by sadra on 10/31/14.
 */
@Repository(value="customerDao")
public class CustomerDaoImpl extends AbstractDao <Customer>
        implements CustomerDao {

    public CustomerDaoImpl() {
        super(Customer.class);
    }

    @Override
    public void save(Customer p) {
        super.save(p);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Customer> list() {
        return super.findAll();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Customer findById(int id) {
        return super.findById(id);
    }
}
