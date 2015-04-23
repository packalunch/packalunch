package com.main.controller;

import com.main.model.dto.CustomerDto;
import com.main.model.dto.DinerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.inject.Inject;
import java.security.Principal;

/**
 * sadra
 * Created by sadra on 12/10/14.
 */
@Controller
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private Facebook facebook;

    private final ProviderSignInUtils providerSignInUtils;

    @Inject
    private ConnectionRepository connectionRepository;

    @Inject
    public AuthController(Facebook facebook) {
        this.facebook = facebook;
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
    CustomerDto getSocialUser(WebRequest request) {

        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
        DinerDto dinerDto = new DinerDto();
        if (connection != null) {
            dinerDto.setFirst_name(connection.fetchUserProfile().getFirstName())
                    .setLast_name(connection.fetchUserProfile().getLastName());

            System.out.println(
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

        System.out.println("in sign up");

        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);

        if (connection != null) {

            System.out.println(
                "======== " + connection.fetchUserProfile().getEmail()
                + "======== " + connection.fetchUserProfile().getUsername()
                + "======== " + connection.fetchUserProfile().getName()
                + "======== " + connection.fetchUserProfile().getFirstName()
                + "======== " + connection.getDisplayName()
                + "======== " + connection.getImageUrl()
            );
        } else {
            System.out.println("nothing ");
        }

        return "redirect:/#/socialsignup";

    }
}
