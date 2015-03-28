package com.curry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * CurryWithAri
 * Created by sadra on 3/10/15.
 */
@Configuration
@ComponentScan(basePackages = {
        "com.curry.service",
        "com.curry.plugins.date"
})
@Import({WebAppContext.class, PersistenceContext.class, SecurityContext.class, SocialContext.class})

public class ApplicationContext {

    @Bean
    public PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
