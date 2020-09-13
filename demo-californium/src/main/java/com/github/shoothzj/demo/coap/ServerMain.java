package com.github.shoothzj.demo.coap;

import org.eclipse.californium.core.CoapServer;

public class ServerMain {

    public static void main(String[] args) {
        CoapServer coapServer = new CoapServer();
        coapServer.add(new MyResource("hello"));
        coapServer.start();
    }

}
