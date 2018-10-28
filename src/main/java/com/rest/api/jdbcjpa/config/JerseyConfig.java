package com.rest.api.jdbcjpa.config;

import com.rest.api.jdbcjpa.provider.ObjectMapperProvider;
import com.rest.api.jdbcjpa.resource.JpaJdbcResource;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("servico/api/*")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        super();
        packages("com.rest.api.jdbcjpa");
        register(JpaJdbcResource.class);
        register(ObjectMapperProvider.class);
    }
}
