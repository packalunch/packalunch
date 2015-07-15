package com.main.service.registration;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.main.base.BaseTest;
import com.main.exception.user.UserEmailExistException;
import com.main.exception.user.UserEmailInvalidException;
import com.main.model.dto.CredentialDto;
import com.main.model.dto.UserDto;
import com.main.model.user.Customer;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * PackALunch
 * Created by Sadra on 7/11/15.
 */
@DatabaseSetup("registrationTest.xml")
public class RegistrationServiceImplTest extends BaseTest {

    static Logger LOGGER = Logger.getLogger(RegistrationServiceImplTest.class);

    @Autowired
    private RegistrationService registrationService;


    @Test
    public void testRegisterCustomer() throws Exception {

        CredentialDto credentialDto = new CredentialDto().setPassword("myPassword");

        UserDto userDto = new UserDto();
        userDto.setFirstName("immanuel")
                .setLastName("kant")
                .setEmail("kant@perpetualPeace.com")
                .setCredentialDto(credentialDto);

        Customer customer = registrationService.registerCustomer(userDto);

        assertNotNull(customer.getId());
    }

    @Test (expected = UserEmailExistException.class)
    public void testRegisterCustomerEmailExistException() throws Exception {
        UserDto userDto = new UserDto().setEmail("gilmour@email.com");
        Customer customer = registrationService.registerCustomer(userDto);
    }

    @Test (expected = UserEmailInvalidException.class)
    public void testRegisterCustomerEmailInvalidEmailException() throws Exception {
        UserDto userDto = new UserDto().setEmail("gilmouremailcom");
        Customer customer = registrationService.registerCustomer(userDto);
    }

}