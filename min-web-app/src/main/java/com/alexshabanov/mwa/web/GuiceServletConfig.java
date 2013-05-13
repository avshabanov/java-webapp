package com.alexshabanov.mwa.web;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.TypeLiteral;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

/**
 * @author Alexander Shabanov
 */
public final class GuiceServletConfig extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new JerseyServletModule() {
            @Override
            protected void configureServlets() {
                /* REST resources */
                bind(SampleResource.class);

                /* reader/writer */
                bind(new TypeLiteral<MessageBodyReader<Object>>() {}).to(new TypeLiteral<JacksonJsonProvider>() {});
                bind(new TypeLiteral<MessageBodyWriter<Object>>() {}).to(new TypeLiteral<JacksonJsonProvider>() {});

                /* services */
                bind(SampleService.class).to(DefaultSampleService.class);

                serve("/rest/*").with(GuiceContainer.class);
            }
        });
    }
}
