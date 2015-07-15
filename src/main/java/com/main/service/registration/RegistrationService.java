package com.main.service.registration;

import com.main.model.dto.UserDto;
import com.main.model.user.Customer;

/**
 * PackALunch
 * Created by Sadra on 7/10/15.
 */
public interface RegistrationService {

    Customer registerCustomer (UserDto userDto);
    Customer registerCustomerAndLogin (UserDto userDto);

}
