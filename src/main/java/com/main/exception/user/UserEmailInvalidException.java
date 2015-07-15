package com.main.exception.user;

import org.apache.log4j.Logger;

/**
 * PackALunch
 * Created by Sadra on 7/11/15.
 */
public class UserEmailInvalidException extends RuntimeException {

    static Logger LOGGER = Logger.getLogger(UserEmailInvalidException.class);
    public UserEmailInvalidException(String message) {
        super(message);
        LOGGER.debug(message);
    }
}
