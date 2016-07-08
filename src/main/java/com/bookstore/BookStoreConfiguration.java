package com.bookstore;

import com.bookstore.core.configs.SwaggerConfig;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookStoreConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty
    private DataSourceFactory databaseConfiguration = new DataSourceFactory();


    @Valid
    @NotNull
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonProperty
    private SwaggerConfig swaggerConfig;

    public SwaggerConfig getSwaggerConfig() {
        return swaggerConfig;
    }
}
