package com.main.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * sadra
 * Created by sadra on 3/10/15.
 */
@Configuration
@EnableJpaRepositories(basePackages = {

        "com.main.fw.model",
        "com.main.dao",
        "com.main.model",

})
@EnableTransactionManagement

@PropertySources({
//        @PropertySource("file:/opt/sonic/configs/jdbc.properties"),
        @PropertySource("classpath:jdbc.properties"),
        @PropertySource("classpath:social.properties")
})
public class PersistenceContext {

    private static final String[] PROPERTY_PACKAGES_TO_SCAN = {
            "com.main.fw.model",
            "com.main.dao",
            "com.main.model",
    };

    protected static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
    protected static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
    protected static final String PROPERTY_NAME_DATABASE_URL = "db.url";
    protected static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";

    @Resource
    private Environment env;

    @Bean
    public DataSource dataSource() {
        final BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
        dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
        dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));

        return dataSource;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan(PROPERTY_PACKAGES_TO_SCAN);

        Properties jpaProperties = new Properties();
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_DIALECT,
                env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL,
                env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO,
                env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO));
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY,
                env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY));
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL,
                env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));

        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(PROPERTY_PACKAGES_TO_SCAN);
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    final Properties hibernateProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO,
                env.getProperty(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO));
        hibernateProperties.setProperty(PROPERTY_NAME_HIBERNATE_DIALECT,
                env.getProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
        hibernateProperties.setProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL,
                env.getProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
        hibernateProperties.setProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL,
                env.getProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));

        return hibernateProperties;
    }

}
