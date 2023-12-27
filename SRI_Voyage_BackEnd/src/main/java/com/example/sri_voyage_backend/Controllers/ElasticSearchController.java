package com.example.sri_voyage_backend.Controllers;

import com.example.sri_voyage_backend.Entities.SearchDocument;
import com.example.sri_voyage_backend.Services.ElasticClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ElasticSearchController {
    ElasticClient elasticClient;
    public ElasticSearchController(ElasticClient elasticClient){
        this.elasticClient=elasticClient;
    }

    @GetMapping("/query/{searchtext}")
    public SearchDocument query(@PathVariable String searchtext){
        SearchDocument searchDocument=new SearchDocument();
        return searchDocument;
    }
}
