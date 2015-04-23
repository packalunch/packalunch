package com.main.controller;

import com.main.fw.json.Response;
import com.main.model.Customer;
import com.main.model.dto.CustomerDto;
import com.main.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.transaction.Transactional;

/**
 * sadra
 * Created by sadra on 12/10/14.
 */
@Controller
public class RegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
    public static final String USER_REGISTER_FAILED = "USER_REGISTER_FAILED";

    private final ProviderSignInUtils providerSignInUtils;

    public RegistrationController() {
        this.providerSignInUtils = new ProviderSignInUtils();
    }

    @Autowired
    private CustomerService customerService;


    @Transactional
    @RequestMapping(value = "api/register", method = RequestMethod.POST)
    public @ResponseBody
    Response registerCustomer(@RequestBody CustomerDto customerDto, WebRequest request) {

        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);

        if (connection != null) {

            System.out.println(
                    "in register" + connection.fetchUserProfile().getEmail()
                            + "======== " + connection.fetchUserProfile().getUsername()
                            + "======== " + connection.fetchUserProfile().getName()
                            + "======== " + connection.fetchUserProfile().getFirstName()
                            + "======== " + connection.getDisplayName()
                            + "======== " + connection.getImageUrl()
            );

            customerDto.setFirst_name(connection.fetchUserProfile().getFirstName())
                    .setLast_name(connection.fetchUserProfile().getLastName());

            Customer customer = customerService.saveCustomer(customerDto);

            System.out.println("customer saved " + customer.getId());
            return new Response("success", String.valueOf(customer.getId()));
        } else {
            System.out.println("nothing ");
            return new Response("failed", USER_REGISTER_FAILED);
        }

    }

}
