package com.example.sri_voyage_backend.Services;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.AnalyzeRequest;
import co.elastic.clients.elasticsearch.indices.AnalyzeResponse;
import co.elastic.clients.elasticsearch.indices.analyze.AnalyzeToken;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.example.sri_voyage_backend.Entities.SearchDocument;
import com.example.sri_voyage_backend.Entities.SearchFilter;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.apache.tomcat.util.buf.StringUtils;
import org.elasticsearch.client.RestClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<SearchDocument> queryDocumentPost(String searchtext, SearchFilter searchFilter)  {
        AnalyzeRequest.Builder analyzeRequestBuilder = new AnalyzeRequest.Builder();
        AnalyzeRequest analyzeRequest = analyzeRequestBuilder.analyzer("french").text(searchtext).build();
        AnalyzeResponse analyzeResponse = null;

        try {
            analyzeResponse = elasticsearchClient.indices().analyze(
                    analyzeRequest
            );
            System.out.println(analyzeResponse);
        } catch (IOException e) {
            System.out.println("Error with Analyzer");
            throw new RuntimeException(e);
        }
        String queryText = "";
        //Construct Queries
        List<Query> queryList = new ArrayList<>();
        for(AnalyzeToken word : analyzeResponse.tokens()) {
            queryList.add(Query.of(sh->sh.fuzzy(f->f.field("description").value(word.token()))));
            queryList.add(Query.of(sh->sh.fuzzy(f->f.field("activities").value(word.token()))));
            queryList.add(Query.of(sh->sh.fuzzy(f->f.field("name").value(word.token()))));
            queryText = queryText+" ";

        }

        List<Query> queryFilterList =  new ArrayList<>();

        if(searchFilter.getSelectedCity()!=null && searchFilter.getSelectedCity()!=null)
        {
            Query byName = MatchQuery.of(m -> m
                    .field("city")
                    .query(searchFilter.getSelectedCity())
            )._toQuery();
            queryFilterList.add(byName);
        }
        if(searchFilter.getMaxBudget()!=null)
        {
            Double min = searchFilter.getMinBudget()!=null?searchFilter.getMinBudget():0;
            Query byMaxPrice = RangeQuery.of(r -> r
                    .field("price").gte(JsonData.of(min))
                    .lte(JsonData.of(searchFilter.getMaxBudget()))

            )._toQuery();
            queryFilterList.add(byMaxPrice);
        }
        if(segit )
        {

            String activityString =  StringUtils.join(searchFilter.getSelectedActivities(), ' ');
            System.out.println();
            Query byActivitis = MatchQuery.of(m -> m
                    .field("activities")
                    .query(activityString)
            )._toQuery();
            queryFilterList.add(byActivitis);
        }


        SearchResponse<SearchDocument> response = null;
        try {
            response = elasticsearchClient.search(s -> s
                            .index("voyage")
                            .query(q -> q.bool(
                                    b->b.should(
                                            queryList
                                    ).filter(queryFilterList))
                            ),
                    SearchDocument.class
            );



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<SearchDocument> searchDocumentList=new ArrayList<>();


        for(Hit h:response.hits().hits())
        {

            SearchDocument s = (SearchDocument) h.source();
            s.setScore(String.valueOf(h.score()));
            searchDocumentList.add(s);
        }

        searchDocumentList.sort((a,b)->
            Double.compare( Double.valueOf(b.getScore()), Double.valueOf(a.getScore()) )
        );

        return searchDocumentList;
    }

    public List<SearchDocument> queryDocumentGet(String searchtext)  {
        AnalyzeRequest.Builder analyzeRequestBuilder = new AnalyzeRequest.Builder();
        AnalyzeRequest analyzeRequest = analyzeRequestBuilder.analyzer("french").text(searchtext).build();
        AnalyzeResponse analyzeResponse = null;

        try {
            analyzeResponse = elasticsearchClient.indices().analyze(
                    analyzeRequest
            );
        } catch (IOException e) {
            System.out.println("Error with Analyzer");
            throw new RuntimeException(e);
        }
        String queryText = "";
        //Construct Queries
        List<Query> queryList = new ArrayList<>();
        for(AnalyzeToken word : analyzeResponse.tokens()) {
            queryList.add(Query.of(sh->sh.fuzzy(f->f.field("description").value(word.token()))));
            queryList.add(Query.of(sh->sh.fuzzy(f->f.field("activities").value(word.token()))));
            queryList.add(Query.of(sh->sh.fuzzy(f->f.field("name").value(word.token()))));
            queryText = queryText+" ";

        }

        SearchResponse<SearchDocument> response = null;
        try {
            response = elasticsearchClient.search(s -> s
                            .index("voyage")
                            .query(q -> q.bool(
                                    b->b.should(
                                            queryList
                                    ))
                            ),
                    SearchDocument.class
            );


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<SearchDocument> searchDocumentList=new ArrayList<>();


        for(Hit h:response.hits().hits())
        {

            SearchDocument s = (SearchDocument) h.source();
            s.setScore(String.valueOf(h.score()));
            searchDocumentList.add(s);
        }

        searchDocumentList.sort((a,b)->
                Double.compare( Double.valueOf(b.getScore()), Double.valueOf(a.getScore()) )
        );

        return searchDocumentList;
    }

}
