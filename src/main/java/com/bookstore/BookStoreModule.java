package com.bookstore;

import com.bookstore.core.configs.SwaggerConfig;
import com.bookstore.core.service.impl.AuthorServiceImpl;
import com.bookstore.core.service.api.AuthorService;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by keshav.gupta on 08/07/16.
 */
@Slf4j
public class BookStoreModule extends AbstractModule {

    @Override
    protected void configure() {
        //bind(ProxyService.class).to(ProxyClient.class).in(Singleton.class);

        bind(AuthorService.class).to(AuthorServiceImpl.class).in(Singleton.class);
    }

    @Provides
    @Singleton
    private SwaggerConfig providesSwaggerConfig(Provider<BookStoreConfiguration> orchestratorConfigurationProvider) {
        return orchestratorConfigurationProvider.get().getSwaggerConfig();
    }
}

