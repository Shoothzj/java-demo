package com.github.shoothzj.demo.coap;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;

@Slf4j
public class MyResource extends CoapResource {

    public MyResource(String name) {
        super(name);
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        super.handleGET(exchange);
        exchange.respond("hello world");
    }

    @Override
    public void handlePOST(CoapExchange exchange) {
        super.handlePOST(exchange);
        exchange.accept();
        log.info("exchange options is [{}]", exchange.getRequestOptions());
        exchange.respond(CoAP.ResponseCode.CREATED);
    }

}
