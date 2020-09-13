package com.github.shoothzj.demo.coap;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;

public class ClientAsyncMain {

    public static void main(String[] args) throws Exception {

        CoapClient client = new CoapClient("coap://iot.eclipse.org:5683/separate");

        client.get(new CoapHandler() {

            @Override public void onLoad(CoapResponse response) { // also error resp.
                System.out.println( response.getResponseText() );
            }

            @Override public void onError() { // I/O errors and timeouts
                System.err.println("Failed");
            }
        });
    }


}
