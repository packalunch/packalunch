package com.main.helper.auth;

import com.main.fw.auth.UserCookieGenerator;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * PackALunch
 * Created by Sadra on 4/25/15.
 */
@Service
public class SecuritySignInAdapter implements SignInAdapter {

    static Logger LOGGER = Logger.getLogger(SecuritySignInAdapter.class);

    @Override
    public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(localUserId, null, null));

        LOGGER.debug("puting cookies");

//        request.getNativeResponse(HttpServletResponse.class)
//                .addCookie(new Cookie("userID", localUserId));

//        LOGGER.debug("after cookies");

        return null;
    }

}
