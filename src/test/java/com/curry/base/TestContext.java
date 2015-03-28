package com.curry.base;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * CurryWithAri
 * Created by sadra on 3/7/15.
 */
@Configuration
@ComponentScan(basePackages = {
        "com.curry.service",
        "com.curry.plugins.date"
})
public class TestContext {

}
