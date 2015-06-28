package com.main.service;

import com.main.dao.UserDao;
import com.main.model.user.User;
import com.main.model.dto.SocialUserDetail;
import com.main.model.factory.SocialUserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * PackALunch
 * Created by sadra on 2/2/15.
 */
public class UserDetailServiceImpl implements UserDetailsService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    private UserDao userDao;

    @Autowired
    public UserDetailServiceImpl(UserDao userDao) {
        this.userDao = userDao;
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

        User user = userDao.findByEmail(username);
        LOGGER.debug("Found user: {}", user);

        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }

        SocialUserDetail socialUserDetail = SocialUserFactory.build(user);

        LOGGER.debug("Returning user details: {}", socialUserDetail);

        return socialUserDetail;
    }
}
