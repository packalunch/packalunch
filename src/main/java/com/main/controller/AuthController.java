package com.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * sadra
 * Created by sadra on 12/10/14.
 */
@Controller
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    /**
     * Return Principal from Spring Security.
     *
     * @param principal
     * @return
     */
    @RequestMapping(value = "/auth/username", method = RequestMethod.GET)
    public @ResponseBody
    Principal currentUserName(Principal principal) {
        return principal;
    }

    @RequestMapping (value = "/signup", method = RequestMethod.GET)
    private String signup () {
        return "/signup";
    }
}
