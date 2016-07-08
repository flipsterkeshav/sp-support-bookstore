package com.bookstore;

import io.dropwizard.configuration.ConfigurationFactory;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jackson.Jackson;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Validation;
import java.io.File;
import java.util.Properties;

/**
 * Created by keshav.gupta on 08/07/16.
 */
public class Helper {
    @Getter
    public static final String JPA_UNIT = "BookStore";

    @Getter
    @Setter
    private static String configFilename;

    public static BookStoreConfiguration createConfiguration(String configFilename) {
        ConfigurationFactory<BookStoreConfiguration> factory =
                new ConfigurationFactory<>(
                        BookStoreConfiguration.class,
                        Validation.buildDefaultValidatorFactory().getValidator(),
                        Jackson.newObjectMapper(),
                        ""
                );
        BookStoreConfiguration configuration;
        try {
            configuration = factory.build(new File(configFilename));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return configuration;
    }


    public static Properties createPropertiesFromConfiguration(BookStoreConfiguration localConfiguration) {
        DataSourceFactory dataSourceFactory = localConfiguration.getDatabaseConfiguration();

        Properties properties = new Properties();
        //properties.put("hibernate.hbm2ddl.auto", "update");
        //        properties.put("hibernate.show_sql", "true");
        //        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.connection.provider_class", dataSourceFactory.getProperties().get("hibernate.connection.provider_class"));
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        properties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        properties.put("hibernate.connection.url", dataSourceFactory.getUrl());
        properties.put("hibernate.connection.username", dataSourceFactory.getUser());
        properties.put("hibernate.connection.password", dataSourceFactory.getPassword());
        properties.put("hibernate.connection.pool_size", "15");
        properties.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
        properties.put("org.hibernate.envers.audit_table_suffix", "_history");
        properties.put("org.hibernate.envers.audit_table_prefix", "");
        properties.put("hibernate.cache.use_second_level_cache", "false");
        properties.put("hibernate.cache.use_query_cache", "false");
        properties.put("hibernate.connection.isolation", "2");
        properties.put("hibernate.hikari.transactionIsolation", "TRANSACTION_READ_COMMITTED");
        properties.put("hibernate.hikari.maximumPoolSize", dataSourceFactory.getProperties().get("hibernate.hikari.maximumPoolSize"));
        properties.put("hibernate.hikari.idleTimeout", dataSourceFactory.getProperties().get("hibernate.hikari.idleTimeout"));
        properties.put("hibernate.hikari.poolName", dataSourceFactory.getProperties().get("hibernate.hikari.poolName"));
        properties.put("hibernate.hikari.connectionTimeout", dataSourceFactory.getProperties().get("hibernate.hikari.connectionTimeout"));

        return properties;
    }
}

