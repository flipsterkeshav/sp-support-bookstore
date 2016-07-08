package com.bookstore;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * Created by keshav.gupta on 08/07/16.
 */

@Slf4j
public class JpaDatabaseModule extends AbstractModule {
    private Properties properties;
    private String JPA_UNIT;

    public JpaDatabaseModule(Properties properties, String JPA_UNIT){
        super();
        this.properties = properties;
        this.JPA_UNIT = JPA_UNIT;
    }

    @Override
    protected void configure() {
        install(new JpaPersistModule(JPA_UNIT).properties(properties));
        //bind(SpiderDatabaseHealthCheck.class);
    }
}
