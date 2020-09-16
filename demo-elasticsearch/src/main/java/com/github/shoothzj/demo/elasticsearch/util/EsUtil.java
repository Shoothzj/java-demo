package com.github.shoothzj.demo.elasticsearch.util;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class EsUtil {

    private static final RestHighLevelClient client
            = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

    public static RestHighLevelClient getDefaultClient() {
        return client;
    }
}
