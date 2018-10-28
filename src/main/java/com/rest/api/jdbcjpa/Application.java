package com.rest.api.jdbcjpa;

import com.rest.api.jdbcjpa.config.JerseyConfig;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import lombok.extern.log4j.Log4j;
import org.glassfish.jersey.servlet.ServletContainer;
import org.jboss.weld.environment.servlet.Listener;

import javax.servlet.ServletException;

import static io.undertow.servlet.Servlets.listener;

@Log4j
public class Application {

    private static Undertow server;
    private static DeploymentManager deploymentManager;
    private static final Integer DEFAULT_PORT = 9002;

    public static void main(String[] args) throws ServletException {
        startContainer(DEFAULT_PORT);
    }

    public static void startContainer(int port) throws ServletException {
        log.info(String.format("STARTING APP ON PORT %d", port));

        PathHandler path = Handlers.path();

        server = Undertow.builder()
                .addHttpListener(port, "localhost")
                .setHandler(path)
                .build();

        server.start();

        log.info(String.format("SERVER STARTED ON PORT %d", port));

        DeploymentInfo servletBuilder = Servlets.deployment()
                .setClassLoader(Application.class.getClassLoader())
                .setContextPath("/servico")
                .addListeners(listener(Listener.class))
                .setResourceManager(new ClassPathResourceManager(Application.class.getClassLoader()))
                .addServlets(
                        Servlets.servlet("jerseyServlet", ServletContainer.class)
                                .setLoadOnStartup(1)
                                .addInitParam("javax.ws.rs.Application", JerseyConfig.class.getName())
                                .addMapping("/api/*"))
                .setDeploymentName("servico.war");

        log.info("STARTING APPLICATION DEPLOYMENT");

        deploymentManager = Servlets.defaultContainer().addDeployment(servletBuilder);
        deploymentManager.deploy();

        try {
            path.addPrefixPath("/servico", deploymentManager.start());
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

        log.info("APPLICATION DEPLOYED");
    }

    public static void stopServer() {

        if (server == null) {
            throw new IllegalStateException("SERVER HAS NOT BEEN STARTED YET");
        }

        log.info("STOPPING SERVER");

        deploymentManager.undeploy();
        server.stop();

        log.info("SERVER STOPPED");
    }
}
