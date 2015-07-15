package com.main.base;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * PackALunch
 * Created by sadra on 3/7/15.
 */
@Configuration
@ComponentScan(basePackages = {
        "com.main.service",
        "com.main.plugins",
        "com.main.helper"
})
public class TestContext {

}
