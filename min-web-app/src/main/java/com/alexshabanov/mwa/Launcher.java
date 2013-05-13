package com.alexshabanov.mwa;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Entry point.
 */
public final class Launcher {
    public static void main(String[] args) throws Exception {
        final Server server = new Server();

        final SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(8080);
        server.addConnector(connector);

        final WebAppContext context = new WebAppContext("src/main/webapp", "/");
        server.setHandler(context);
        server.start();
    }
}
