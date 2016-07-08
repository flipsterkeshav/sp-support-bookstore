package com.bookstore;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.inject.Stage;
import com.google.inject.persist.PersistFilter;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.sun.jersey.api.container.filter.LoggingFilter;
import com.sun.jersey.api.core.ResourceConfig;
import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.config.SwaggerConfig;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerDropwizard;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

@Slf4j
public class BookStoreApplication extends Application<BookStoreConfiguration> {

    private final SwaggerDropwizard swaggerDropwizard = new SwaggerDropwizard();
    private GuiceBundle<BookStoreConfiguration> guiceBundle;

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < args.length; i++) {
            if (args[i].endsWith(".yml")||args[i].endsWith(".yaml")) {
                Helper.setConfigFilename(args[i]);
            }
        }
        Helper.setConfigFilename("config/config.yml");
        new BookStoreApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<BookStoreConfiguration> bootstrap) {
        String configFileName = Helper.getConfigFilename();
        if(configFileName==null)
            configFileName ="config/config.yml";
            //configFileName = "src/test/resources/testConfig.yaml";
        BookStoreConfiguration bookStoreConfiguration = Helper.createConfiguration(configFileName);

        guiceBundle = GuiceBundle.<BookStoreConfiguration>newBuilder()
                .addModule(new BookStoreModule())
                .addModule(new JpaDatabaseModule(Helper.createPropertiesFromConfiguration(bookStoreConfiguration), Helper.getJPA_UNIT()))
                .enableAutoConfig("com.bookstore")
                .setConfigClass(BookStoreConfiguration.class).build(Stage.DEVELOPMENT);

        bootstrap.addBundle(guiceBundle);
        bootstrap.addBundle(new AssetsBundle("/public", "/health", "", "health"));
        swaggerDropwizard.onInitialize(bootstrap);
    }

    @Override
    public void run(BookStoreConfiguration configuration, Environment environment) throws Exception {
        environment.servlets().addFilter("persistFilter", guiceBundle.getInjector()
                .getInstance(PersistFilter.class))
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

        //environment.jersey().getResourceConfig().getResourceFilterFactories().add(guiceBundle.getInjector().getInstance(AuthorisationFilterFactory.class));

        FilterRegistration.Dynamic filter =environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        filter.setInitParameter("allowCredentials", "true");

        environment.jersey().property(ResourceConfig.PROPERTY_CONTAINER_REQUEST_FILTERS, LoggingFilter.class.getName());
        environment.jersey().property(ResourceConfig.PROPERTY_CONTAINER_RESPONSE_FILTERS, LoggingFilter.class.getName());


        environment.getObjectMapper().configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

//        final JmxReporter reporter = JmxReporter.forRegistry(environment.metrics()).build();
//        reporter.start();

        SwaggerConfig config = ConfigFactory.config();
        config.setApiVersion("1.0.1");
        swaggerDropwizard.onRun(configuration, environment, configuration.getSwaggerConfig().getSwaggerHost(), configuration.getSwaggerConfig().getSwaggerPort());

        ServiceFactory.setGuiceBundle(guiceBundle);
        //        if(!configuration.getRestBusConfig().getRestEnvName().equals("local"))
       // initializeServiceFactory();
    }


//    private void initializeServiceFactory() {
//        ConfigurationBuilder builder=new ConfigurationBuilder();
//        builder.addUrls(ClasspathHelper.forPackage("com.fquick.spider.core.service.api"));
//        builder.setScanners(new TypeAnnotationsScanner());
//        Reflections reflections = new Reflections(builder);
//        Set<Class<?>> serviceClasses = reflections.getTypesAnnotatedWith(Service.class);
//        for (Class<?> service : serviceClasses) {
//            log.info("Class found " + service.getSimpleName());
//            ServiceFactory.serviceMap.put(service.getSimpleName(),guiceBundle.getInjector().getInstance(service));
//        }
//    }

}


