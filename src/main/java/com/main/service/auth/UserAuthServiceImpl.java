package com.main.service.auth;

import com.main.dao.UserDao;
import com.main.helper.auth.SecuritySignInAdapter;
import com.main.model.billing.Account;
import com.main.model.dto.Role;
import com.main.model.dto.SocialMediaService;
import com.main.model.dto.UserDto;
import com.main.model.user.Credential;
import com.main.model.user.Customer;
import com.main.model.user.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * PackALunch
 * Created by Sadra on 7/1/15.
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {

    static Logger LOGGER = Logger.getLogger(UserAuthServiceImpl.class);

    @Autowired
    private SecuritySignInAdapter securitySignInAdapter;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private UserDao userDao;

    /**
     * Save a user in DB
     * @return
     */
    @Override
    public boolean registerFacebookUser(Connection<?> connection) {

        List<String> userIds = usersConnectionRepository.findUserIdsWithConnection(connection);
        LOGGER.debug("user IDs:::" + userIds.toString());

        if (userIds.isEmpty()) {

            LOGGER.debug("user IDs:::" + userIds.toString());

            User user = getNewFacebookCustomer(connection);

            userDao.save(user);


            usersConnectionRepository
                    .createConnectionRepository(Integer.toString(user.getId()))
                    .addConnection(connection);


            securitySignInAdapter.signIn(Integer.toString(user.getId()), null, null);

            LOGGER.debug("customer saved " + user.getEmail()
                    + "  ID:  " + user.getId());

            return true;

        } else {
            LOGGER.debug("customer already exists");
            return false;
        }

    }





    private User getNewFacebookCustomer(Connection<?> connection) {

        User user = new Customer();
        user.setFirst_name(connection.fetchUserProfile().getFirstName())
                .setLast_name(connection.fetchUserProfile().getLastName())
                .setEmail(connection.fetchUserProfile().getEmail());

        Account account = new Account();
        account.setUser(user);
        user.setAccount(account);

        Credential credential = new Credential();
        credential
                .setRole(Role.ROLE_USER)
                .setSignInProvider(SocialMediaService.FACEBOOK)
                .setUser(user)
                .setPassword("").setSalt("");


        user.setCredential(credential);

        return user;
    }
}
