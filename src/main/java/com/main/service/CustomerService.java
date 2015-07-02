package com.main.service;

import com.main.model.dto.UserDto;
import com.main.model.user.User;
import com.main.model.dto.DinerDto;
import com.main.plugins.date.Week;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PackALunch
 * Created by sadra on 11/2/14.
 */
@Service
public interface CustomerService {

    UserDto findCustomerById (int id);
    UserDto findByUsername (String userName);
    User getCustomerById (int id);
    List <UserDto> findCustomers ();
    User saveCustomer (UserDto userDto);
    User updateCustomer (UserDto userDto);
    void deleteCustomer (int id);

    List <DinerDto> getDiners (Week week);

    void savePayment(DinerDto dinerDto);

    DinerDto getDiner(int id, Week week);

}
