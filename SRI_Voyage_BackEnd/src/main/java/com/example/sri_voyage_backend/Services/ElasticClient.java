package com.example.sri_voyage_backend.Services;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.AnalyzeRequest;
import co.elastic.clients.elasticsearch.indices.AnalyzeResponse;
import co.elastic.clients.elasticsearch.indices.analyze.AnalyzeToken;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.example.sri_voyage_backend.Entities.SearchDocument;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter
public class ElasticClient {
    String serverUrl = "https://48cf8c7b250b4bd1984f99117884d19c.us-central1.gcp.cloud.es.io";
    String apiKey = "T2J1bHBZd0JXOUNQRVllZWp1c3g6QXpadjhZMVpTVHllektROGpiUFBJZw==";

    ElasticsearchClient elasticsearchClient;

    public ElasticClient(){
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
        this.elasticsearchClient = new ElasticsearchClient(transport);
//        return esClient;
    }

    public List<SearchDocument> queryDocument( String searchtext)  {
        AnalyzeRequest.Builder analyzeRequestBuilder = new AnalyzeRequest.Builder();
        AnalyzeRequest analyzeRequest = analyzeRequestBuilder.analyzer("french").text(searchtext).build();
        AnalyzeResponse analyzeResponse = null;
//        System.out.println("query");
//        System.out.println(elasticsearchClient);
        try {
            analyzeResponse = elasticsearchClient.indices().analyze(
                    analyzeRequest
            );
        } catch (IOException e) {
            System.out.println("Error with Analyzer");
            throw new RuntimeException(e);
        }

        StringBuilder stringBuilder=new StringBuilder();
        for(AnalyzeToken tok : analyzeResponse.tokens())
        {
            stringBuilder.append(tok.token()+" ");
//            analysedSearchText=analysedSearchText+" "+tok.token();
        }

        String finalAnalysedSearchText = stringBuilder.toString();
        SearchResponse<SearchDocument> response = null;
        try {
            response = elasticsearchClient.search(s -> s
                            .index("voyage")
                            .query(q -> q
                                    .match(t -> t
                                            .field("description")
                                            .query(finalAnalysedSearchText)
                                    )
                            ),
                    SearchDocument.class
            );
        } catch (IOException e) {
            System.out.println("Error with search");
            throw new RuntimeException(e);
        }


        List<SearchDocument> searchDocumentList=new ArrayList<>();


        for(Hit h:response.hits().hits())
        {

            SearchDocument s = (SearchDocument) h.source();
            s.setScore(String.valueOf(h.score()));
            searchDocumentList.add(s);
            System.out.println("/////////////////////////////////////////////");
            System.out.println("Score");
            System.out.println(h.source());
            System.out.println(h.score());

        }

        searchDocumentList.sort((a,b)->
            Double.compare( Double.valueOf(b.getScore()), Double.valueOf(a.getScore()) )
        );

        return searchDocumentList;
    }

}
