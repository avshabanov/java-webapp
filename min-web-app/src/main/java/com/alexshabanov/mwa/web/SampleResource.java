package com.alexshabanov.mwa.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Alexander Shabanov
 */
@Path("/")
public final class SampleResource {

    @GET
    @Produces("text/plain")
    @Path("/hello")
    public String getHello() {
        return "Hello from Jersey Resource!";
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/message/{param}")
    public SampleMessage getMessage(@PathParam("param") String param) {
        return SampleMessage.from("message=" + param);
    }

    @XmlRootElement
    public static final class SampleMessage {
        public String text;

        public static SampleMessage from(String text) {
            final SampleMessage message = new SampleMessage();
            message.text = text;
            return message;
        }
    }
}
