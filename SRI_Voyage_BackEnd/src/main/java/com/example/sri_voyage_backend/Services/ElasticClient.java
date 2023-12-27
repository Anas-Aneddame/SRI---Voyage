package com.example.sri_voyage_backend.Services;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.springframework.stereotype.Component;

@Component
public class ElasticClient {
    String serverUrl = "https://48cf8c7b250b4bd1984f99117884d19c.us-central1.gcp.cloud.es.io";
    String apiKey = "T2J1bHBZd0JXOUNQRVllZWp1c3g6QXpadjhZMVpTVHllektROGpiUFBJZw==";

    public ElasticsearchClient getEsClient(){
        // Create the low-level client
        RestClient restClient = RestClient
                .builder(HttpHost.create(serverUrl))
                .setDefaultHeaders(new Header[]{
                        new BasicHeader("Authorization", "ApiKey " + apiKey)
                })
                .build();

        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

        // And create the API client
        ElasticsearchClient esClient = new ElasticsearchClient(transport);
        return esClient;
    }


}
