package com.github.shoothzj.demo.coap;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.elements.exception.ConnectorException;

import java.io.IOException;

import static org.eclipse.californium.core.coap.MediaTypeRegistry.APPLICATION_XML;
import static org.eclipse.californium.core.coap.MediaTypeRegistry.TEXT_PLAIN;

public class ClientMain {

    private static final String multiFormatUrl = "coap://iot.eclipse.org:5683/multi-format";

    private static final String testUrl = "coap://iot.eclipse.org:5683/test";

    public static void main(String[] args) throws ConnectorException, IOException {
        CoapClient client1 = new CoapClient(multiFormatUrl);
        String text = client1.get().getResponseText(); // blocking call
        String xml = client1.get(APPLICATION_XML).getResponseText();

        CoapClient client2 = new CoapClient(testUrl);

        CoapResponse resp = client2.put("payload", TEXT_PLAIN); // for response details
        System.out.println( resp.isSuccess() );
        System.out.println( resp.getOptions() );

        client2.useNONs();  // use autocomplete to see more methods
        client2.delete();
        client2.useCONs().useEarlyNegotiation(32).get(); // it is a fluent API
    }

}
