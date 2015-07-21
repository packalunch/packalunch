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
public class UserDetailServiceImpl implements UserDetailsService {

    static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(UserDetailServiceImpl.class);

    private UserDao userDao;

    @Autowired
    public UserDetailServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Loads the user information.
     *
     * @param username The username of the requested user.
     * @return The information of the user.
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException Thrown if no user is found with the given username.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LOGGER.debug("Loading user by username: " + username);

        User user = null;

        if (isNumeric(username)) { // find by user id if social user
            user = userDao.findOne(Integer.parseInt(username));
        } else { // find by email if local user
            user = userDao.findByEmail(username);
        }

        if (user == null) {
            LOGGER.debug("no user found: " + username);
            throw new UsernameNotFoundException("No user found with username: " + username);
        }
        LOGGER.debug("Found userID:" + user.getId());

        SocialUserDetail socialUserDetail = SocialUserFactory.build(user);

        LOGGER.debug("Returning user details: " + socialUserDetail.toString());

        return socialUserDetail;
    }

    private static boolean isNumeric(String str) {
        try {
            Integer integer = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
