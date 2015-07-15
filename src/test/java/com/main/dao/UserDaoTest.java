package com.main.dao;

import com.main.base.BaseTest;
import com.main.model.user.Credential;
import com.main.model.user.Customer;
import com.main.model.user.User;
import com.main.model.dto.Role;
import com.main.model.dto.SocialMediaService;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * PackALunch
 * Created by sadra on 11/15/14.
 */
@DatabaseSetup("userDaoTest.xml")
public class UserDaoTest extends BaseTest {



    @Autowired
    private UserDao userDao;

    @Test
    public void testFindCustomer () {
        User user =  userDao.findOne(1);
        assertNotNull(user.getId());
        assertTrue(user instanceof Customer);
    }

    @Test
    public void testSaveCustomer () {
        Customer customer = new Customer();
        customer.setFirst_name("davidTest")
                .setEmail("gilmour@email.com")
                .setLast_name("gilmourTest")
                .setAddress("address st");

        userDao.save(customer);
        assertNotNull(customer.getId());
    }

    @Test
    public void testSaveCustomerWithCredential () {
        User user = new Customer();
        user.setFirst_name("rogerTest")
                .setLast_name("watersTest")
                .setEmail("email@email.com");

        Credential credential = new Credential();
        credential.setPassword("123")
                .setSalt("123qwe")
                .setRole(Role.ROLE_USER)
                .setSignInProvider(SocialMediaService.FACEBOOK)
                .setUser(user);

        user.setCredential(credential);
        userDao.save(user);
        assertNotNull(user.getId());
        User expectedUser = userDao.findOne(user.getId());
        assertNotNull(expectedUser.getCredential());
        assertTrue(expectedUser instanceof Customer);
        assertEquals(expectedUser.getCredential().getRole(), Role.ROLE_USER);

    }

    @Test
    public void testSaveCredentialExistingCustomer () {
        User user = userDao.findOne(1);
        Credential credential = user.getCredential();
        credential.setPassword("new password");
        user.setCredential(credential);
        userDao.save(user);

        User expectedUser = userDao.findOne(user.getId());
        assertEquals(expectedUser.getCredential().getPassword(), "new password");
    }

    @Test
    public void testFindCustomerByEmail () {
        User user = userDao.findByEmail("waters@email.com");
        assertNotNull(user);
        assertEquals(user.getFirst_name(), "roger");
    }

    @Test
    public void testUpdateCustomer () {
        User actual =  userDao.findOne(2);
        actual.setFirst_name("FTest").setLast_name("LTest").setAddress("address st");

        userDao.saveAndFlush(actual);

        User updated = userDao.findOne(actual.getId());

        assertEquals(actual.getFirst_name(), updated.getFirst_name());
        assertEquals(actual.getId(), updated.getId());

    }

    @Test
    public void testListCustomer () {
        User user1 = new User();
        user1.setFirst_name("david").setLast_name("gilmour");

//        List<User> userList = userDao.findByUser_type("customer");
//
//        assertEquals(3, userList.size());
//        assertEquals (user1.getFirstName(), userList.get(0).getFirstName());
    }

    @Test
    public void testGetCustomer () {
        User user1 = new User();
        user1.setFirst_name("david").setLast_name("gilmour");
        User user = userDao.findOne(1);
        assertEquals (user.getFirst_name(), user1.getFirst_name());
    }

    @Test
    public void testGetCustomerWithAccount () {
        User user = userDao.findOne(1);
        assertNotNull(user.getAccount());
    }

    @Test
    public void testDeleteCustomer () {
        userDao.delete(3);
        assertFalse(userDao.exists(3));
    }


}
