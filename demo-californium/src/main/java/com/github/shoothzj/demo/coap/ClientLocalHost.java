package com.github.shoothzj.demo.coap;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.elements.exception.ConnectorException;

import java.io.IOException;

import static org.eclipse.californium.core.coap.MediaTypeRegistry.APPLICATION_XML;
import static org.eclipse.californium.core.coap.MediaTypeRegistry.TEXT_PLAIN;

@Slf4j
public class ClientLocalHost {

    public static void main(String[] args) throws ConnectorException, IOException {
        CoapClient client1 = new CoapClient("coap://127.0.0.1:5683/multi-format");
        String text = client1.get().getResponseText(); // blocking call
        String xml = client1.get(APPLICATION_XML).getResponseText();
        log.info("text is [{}], xml is [{}]", text, xml);
    }

}
