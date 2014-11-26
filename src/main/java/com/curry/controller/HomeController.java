package com.curry.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class HomeController {

    @RequestMapping(value="/home")
    public ModelAndView load() throws IOException {
        return new ModelAndView("home");
    }

    @RequestMapping(value="/order")
    public ModelAndView order() throws IOException {

        return new ModelAndView("home");
    }

}
