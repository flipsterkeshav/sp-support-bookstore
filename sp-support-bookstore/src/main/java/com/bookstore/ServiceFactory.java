package com.bookstore;

import com.hubspot.dropwizard.guice.GuiceBundle;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by keshav.gupta on 08/07/16.
 */

public class ServiceFactory {
    public static Map<String, Object> serviceMap = new HashMap<String, Object>();
    public static GuiceBundle<BookStoreConfiguration> guiceBundle = null;

    public static void setGuiceBundle(GuiceBundle<BookStoreConfiguration> bundle) {
        if(guiceBundle == null)
            guiceBundle = bundle;
    }

    public static <T> T getService(Class<T> className) {
        if(!serviceMap.containsKey(className.getSimpleName()))
            serviceMap.put(className.getSimpleName(),guiceBundle.getInjector().getInstance(className));
        return (T) serviceMap.get(className.getSimpleName());
    }
}
