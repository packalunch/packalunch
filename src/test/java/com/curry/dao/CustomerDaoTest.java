package com.curry.dao;

import com.curry.base.BaseTest;
import com.curry.model.Credential;
import com.curry.model.Customer;
import com.curry.model.dto.Role;
import com.curry.model.dto.SocialMediaService;
import com.curry.service.CustomerService;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * CurryWithAri
 * Created by sadra on 11/15/14.
 */
@DatabaseSetup("customerDaoTest.xml")
public class CustomerDaoTest extends BaseTest {


    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testSaveCustomer () {
        Customer customer1 = new Customer();
        customer1.setFirst_name("davidTest")
                .setEmail("gilmour@email.com")
                .setLast_name("gilmourTest")
                .setAddress("address st");

        customerDao.save(customer1);
        assertNotNull(customer1.getId());
    }

    @Test
    public void testSaveCustomerWithCredential () {
        Customer customer = new Customer();
        customer.setFirst_name("rogerTest")
                .setLast_name("watersTest")
                .setEmail("email@email.com");

        Credential credential = new Credential();
        credential.setPassword("123")
                .setSalt("123qwe")
                .setRole(Role.ROLE_USER)
                .setSignInProvider(SocialMediaService.FACEBOOK)
                .setCustomer(customer);

        customer.setCredential(credential);
        customerDao.save(customer);
        assertNotNull(customer.getId());
        Customer expectedCustomer = customerDao.findOne(customer.getId());
        assertNotNull(expectedCustomer.getCredential());

        assertEquals(expectedCustomer.getCredential().getRole(), Role.ROLE_USER);

    }

    @Test
    public void testSaveCredentialExistingCustomer () {
        Customer customer = customerDao.findOne(1);
        Credential credential = customer.getCredential();
        credential.setPassword("new password");
        customer.setCredential(credential);
        customerDao.save(customer);

        Customer expectedCustomer = customerDao.findOne(customer.getId());
        assertEquals(expectedCustomer.getCredential().getPassword(), "new password");
    }

    @Test
    public void testFindCustomerByEmail () {
        Customer customer = customerDao.findByEmail("waters@email.com");
        assertNotNull(customer);
        assertEquals(customer.getFirst_name(), "roger");
    }

    @Test
    public void testUpdateCustomer () {
        Customer actual =  customerDao.findOne(2);
        actual.setFirst_name("FTest").setLast_name("LTest").setAddress("address st");

        customerDao.saveAndFlush(actual);

        Customer updated = customerDao.findOne(actual.getId());

        assertEquals(actual.getFirst_name(), updated.getFirst_name());
        assertEquals(actual.getId(), updated.getId());

    }

    @Test
    public void testListCustomer () {
        Customer customer1 = new Customer();
        customer1.setFirst_name("david").setLast_name("gilmour");

        List<Customer> customerList = customerDao.findAll();

        assertEquals(3, customerList.size());
        assertEquals (customer1.getFirst_name(), customerList.get(0).getFirst_name());
    }

    @Test
    public void testGetCustomer () {
        Customer customer1 = new Customer();
        customer1.setFirst_name("david").setLast_name("gilmour");
        Customer customer = customerDao.findOne(1);
        assertEquals (customer.getFirst_name(), customer1.getFirst_name());
    }

    @Test
    public void testGetCustomerWithAccount () {
        Customer customer = customerDao.findOne(1);
        assertNotNull(customer.getAccount());
    }

    @Test
    public void testDeleteCustomer () {
        customerDao.delete(3);
        assertFalse(customerDao.exists(3));
    }


}
