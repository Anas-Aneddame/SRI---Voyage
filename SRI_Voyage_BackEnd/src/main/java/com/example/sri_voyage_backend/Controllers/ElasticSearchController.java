package com.example.sri_voyage_backend.Controllers;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.example.sri_voyage_backend.Entities.SearchDocument;
import com.example.sri_voyage_backend.Services.ElasticClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
public class ElasticSearchController {
    ElasticClient elasticClient;
    public ElasticSearchController(ElasticClient elasticClient  ){
        this.elasticClient=elasticClient;
    }

    @GetMapping("/query/{searchtext}")
    public List<SearchDocument> query(@PathVariable String searchtext){
        return this.elasticClient.queryDocument(searchtext);
    }
}
