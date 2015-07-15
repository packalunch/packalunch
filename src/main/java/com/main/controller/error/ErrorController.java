package com.main.controller.error;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

/**
 * PackALunch
 * Created by Sadra on 7/3/15.
 */
@Controller
public class ErrorController {

    static Logger LOGGER = Logger.getLogger(ErrorController.class);

    @RequestMapping(value = "/error/error", method = RequestMethod.POST)
    private String getClientToken (WebRequest request) {
        LOGGER.debug("in error controller");

        return "redirect:/error/error/500.html";
    }
}
