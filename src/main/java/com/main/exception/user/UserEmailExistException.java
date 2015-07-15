package com.main.exception.user;

import org.apache.log4j.Logger;

/**
 * PackALunch
 * Created by Sadra on 7/11/15.
 */
public class UserEmailExistException extends RuntimeException {

    static Logger LOGGER = Logger.getLogger(UserEmailExistException.class);

    public UserEmailExistException(String message) {
        super(message);

        LOGGER.debug(message);

    }
}
