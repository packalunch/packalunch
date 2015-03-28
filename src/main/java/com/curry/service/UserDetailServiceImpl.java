package com.curry.service;

import com.curry.dao.CustomerDao;
import com.curry.model.Customer;
import com.curry.model.dto.SocialUserDetail;
import com.curry.model.factory.SocialUserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * CurryWithAri
 * Created by sadra on 2/2/15.
 */
public class UserDetailServiceImpl implements UserDetailsService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    private CustomerDao customerDao;

    @Autowired
    public UserDetailServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * Loads the user information.
     * @param username  The username of the requested user.
     * @return  The information of the user.
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException
     * Thrown if no user is found with the given username.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.debug("Loading user by username: {}", username);

        Customer customer = customerDao.findByEmail(username);
        LOGGER.debug("Found user: {}", customer);

        if (customer == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }

        SocialUserDetail socialUserDetail = SocialUserFactory.build(customer);

        LOGGER.debug("Returning user details: {}", socialUserDetail);

        return socialUserDetail;
    }
}
