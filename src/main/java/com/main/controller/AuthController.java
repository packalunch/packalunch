package com.main.controller;

import com.main.model.dto.UserDto;
import com.main.model.dto.DinerDto;
import com.main.service.auth.UserAuthService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.security.Principal;

/**
 * sadra
 * Created by sadra on 12/10/14.
 */
@Controller
public class AuthController {

    static Logger LOGGER = Logger.getLogger(AuthController.class);

    @Autowired
    protected UserAuthService userAuthService;


    private final ProviderSignInUtils providerSignInUtils;

    public AuthController() {
        this.providerSignInUtils = new ProviderSignInUtils();
    }

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


    @RequestMapping(value = "/auth/social", method = RequestMethod.GET)
    public @ResponseBody
    UserDto getSocialUser(WebRequest request) {

        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
        DinerDto dinerDto = new DinerDto();
        if (connection != null) {
            dinerDto.setFirst_name(connection.fetchUserProfile().getFirstName())
                    .setLast_name(connection.fetchUserProfile().getLastName());

            LOGGER.debug(
                    "======== " + connection.fetchUserProfile().getEmail()
                            + "======== " + connection.fetchUserProfile().getUsername()
                            + "======== " + connection.fetchUserProfile().getName()
                            + "======== " + connection.fetchUserProfile().getFirstName()
                            + "======== " + connection.getDisplayName()
                            + "======== " + connection.getImageUrl()
            );
            return dinerDto;
        } else {
            System.out.println("nothing ");
            return dinerDto;
        }

    }


    @RequestMapping (value = "/signup", method = RequestMethod.GET)
    private String signup (WebRequest request) {

        LOGGER.debug("in sign up");

        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);

        if (connection != null) {

            LOGGER.debug(
                    "======== " + connection.fetchUserProfile().getEmail()
                            + "======== " + connection.fetchUserProfile().getUsername()
                            + "======== " + connection.fetchUserProfile().getName()
                            + "======== " + connection.fetchUserProfile().getFirstName()

                            + "======== " + connection.getDisplayName()
                            + "======== " + connection.getImageUrl()

                            + "======== ProfileURL: " + connection.getKey().getProviderUserId()
            );


            userAuthService.registerFacebookUser(connection);

            LOGGER.debug("user saved in UserConnection");
            return "redirect:/#/user";

        } else {
            LOGGER.debug("nothing ");
            return "redirect:/";
        }
    }
}
