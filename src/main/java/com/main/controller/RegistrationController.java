package com.main.controller;

import com.main.exception.user.UserEmailExistException;
import com.main.exception.user.UserEmailInvalidException;
import com.main.fw.json.Response;
import com.main.model.dto.UserDto;
import com.main.model.user.Customer;
import com.main.service.registration.RegistrationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

/**
 * sadra
 * Created by sadra on 12/10/14.
 */
@Controller
public class RegistrationController {

    static Logger LOGGER = Logger.getLogger(RegistrationController.class);

    public static final String USER_REGISTER_FAILED = "USER_REGISTER_FAILED";

    private final ProviderSignInUtils providerSignInUtils;

    public RegistrationController() {
        this.providerSignInUtils = new ProviderSignInUtils();
    }

    @Autowired
    private RegistrationService registrationService;



    @RequestMapping(value = "api/localRegister", method = RequestMethod.POST)
    public @ResponseBody
    Response registerLocalCustomer(@RequestBody UserDto userDto, HttpServletResponse response) {
        LOGGER.debug(userDto.toString());
        try {
            Customer customer = registrationService.registerCustomerAndLogin(userDto);

            response.addCookie(new Cookie("userId", customer.getId().toString()));

            return new Response("success", Integer.toString(customer.getId()));
        } catch (UserEmailExistException ex) {
            return new Response ("failed", ex.getMessage());
        } catch (UserEmailInvalidException ex) {
            return new Response ("failed", ex.getMessage());
        }
    }


    @Transactional
    @RequestMapping(value = "api/register", method = RequestMethod.POST)
    public @ResponseBody
    Response registerCustomer(@RequestBody UserDto userDto, WebRequest request) {

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




            return new Response("success", "234");
        } else {
            System.out.println("nothing ");
            return new Response("failed", USER_REGISTER_FAILED);
        }

    }

}
