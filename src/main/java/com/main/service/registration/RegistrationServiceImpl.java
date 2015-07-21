package com.main.service.registration;

import com.main.dao.UserDao;
import com.main.exception.user.UserEmailExistException;
import com.main.exception.user.UserEmailInvalidException;
import com.main.helper.auth.SecuritySignInAdapter;
import com.main.model.billing.Account;
import com.main.model.dto.Role;
import com.main.model.dto.UserDto;
import com.main.model.user.Credential;
import com.main.model.user.Customer;
import com.main.plugins.validator.EmailValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * PackALunch
 * Created by Sadra on 7/10/15.
 */
@Service
public class RegistrationServiceImpl  implements RegistrationService{


    static Logger LOGGER = Logger.getLogger(RegistrationServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private SecuritySignInAdapter securitySignInAdapter;


    @Override
    @Transactional
    public Customer registerCustomer(UserDto userDto) {

        //validate if email is unique
        if (userDao.findByEmail(userDto.getEmail()) != null) {
            LOGGER.debug("email exists :" + userDto.getEmail());
            throw new UserEmailExistException("email exists: " + userDto.getEmail());
        }

        if (!emailValidator.validate(userDto.getEmail())) {
            LOGGER.debug("email invalid: " + userDto.getEmail());
            throw new UserEmailInvalidException("email invalid: " + userDto.getEmail());
        }

        Customer customer = new Customer();

        customer.setFirst_name(userDto.getFirstName())
                .setLast_name(userDto.getLastName())
                .setEmail(userDto.getEmail());

        Account account = new Account();
        account.setUser(customer);


        String salt = BCrypt.gensalt();
        String password = BCrypt.hashpw(userDto.getCredentialDto().getPassword(), salt);

        Credential credential = new Credential();
        credential
                .setUser(customer)
                .setPassword(password)
                .setRole(Role.ROLE_USER)
                .setSalt(salt);

        customer.setAccount(account).setCredential(credential);

        userDao.save(customer);

        LOGGER.debug(customer);

        return customer;
    }

    @Override
    public Customer registerCustomerAndLogin(UserDto userDto) {

        Customer customer = registerCustomer(userDto);

        securitySignInAdapter.signIn(Integer.toString(customer.getId()),null,null);

        return customer;
    }

}
