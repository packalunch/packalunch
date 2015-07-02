package com.main.service.auth;

import org.springframework.social.connect.Connection;

/**
 * PackALunch
 * Created by Sadra on 7/1/15.
 */

public interface UserAuthService {

    boolean registerFacebookUser ( Connection<?> connection );
}
