package com.example.sri_thematique_voyage;

import com.example.sri_thematique_voyage.Controllers.ElasticClientController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SriThematiqueVoyageApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(SriThematiqueVoyageApplication.class, args);
        ElasticClientController elasticClientController = new ElasticClientController();
    }

}
