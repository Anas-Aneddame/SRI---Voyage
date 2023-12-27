package com.example.sri_voyage_backend.Controllers;


import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.analysis.*;
import co.elastic.clients.elasticsearch._types.mapping.Property;
import co.elastic.clients.elasticsearch._types.query_dsl.Operator;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.*;
import co.elastic.clients.elasticsearch.indices.analyze.AnalyzeToken;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import co.elastic.clients.util.ObjectBuilder;
import com.example.sri_voyage_backend.Entities.Product;
import com.example.sri_voyage_backend.Entities.SearchDocument;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ElasticClientController {
    public ElasticClientController() throws IOException {
    String serverUrl = "https://48cf8c7b250b4bd1984f99117884d19c.us-central1.gcp.cloud.es.io";
    String apiKey = "T2J1bHBZd0JXOUNQRVllZWp1c3g6QXpadjhZMVpTVHllektROGpiUFBJZw==";

    RestClient restClient = RestClient
            .builder(HttpHost.create(serverUrl))
            .setDefaultHeaders(new Header[]{
                    new BasicHeader("Authorization", "ApiKey " + apiKey)
            })
            .build();

    ElasticsearchTransport transport = new RestClientTransport(
            restClient, new JacksonJsonpMapper());
    ElasticsearchClient esClient = new ElasticsearchClient(transport);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





//
//                Product product = new Product("ph-1", "City Phone", 123.0,"Best Phone in the world !");
//
        Map<String, Property> propertyMap = new HashMap<>();
        propertyMap.put("id", new Property.Builder().text(t->t.analyzer("french")).build());
        propertyMap.put("name", new Property.Builder().text(t->t.analyzer("french")).build());
        propertyMap.put("city", new Property.Builder().text(t->t.analyzer("french")).build());
        propertyMap.put("duration", new Property.Builder().text(t->t.analyzer("french")).build());
        propertyMap.put("price", new Property.Builder().text(t->t.analyzer("french")).build());
        propertyMap.put("link", new Property.Builder().text(t->t.analyzer("french")).build());
        propertyMap.put("description", new Property.Builder().text(t->t.analyzer("french")).build());
        propertyMap.put("type", new Property.Builder().text(t->t.analyzer("french")).build());


        List<String> stopWords = List.of("a", "afin", "ai", "ainsi", "après", "attendu", "au", "aujourd", "auquel", "aussi",
                "autre", "autres", "aux", "auxquelles", "auxquels", "avait", "avant", "avec", "avoir",
                "c", "car", "ce", "ceci", "cela", "celle", "celles", "celui", "cependant", "certain",
                "certaine", "certaines", "certains", "ces", "cet", "cette", "ceux", "chez", "ci",
                "combien", "comme", "comment", "concernant", "contre", "d", "dans", "de", "debout",
                "dedans", "dehors", "delà", "depuis", "derrière", "des", "désormais", "desquelles",
                "desquels", "dessous", "dessus", "devant", "devers", "devra", "divers", "diverse",
                "diverses", "doit", "donc", "dont", "du", "duquel", "durant", "dès", "elle", "elles",
                "en", "entre", "environ", "est", "et", "etc", "etre", "eu", "eux", "excepté", "hormis",
                "hors", "hélas", "hui", "il", "ils", "j", "je", "jusqu", "jusque", "l", "la", "laquelle",
                "le", "lequel", "les", "lesquelles", "lesquels", "leur", "leurs", "lorsque", "lui", "là",
                "ma", "mais", "malgré", "me", "merci", "mes", "mien", "mienne", "miennes", "miens", "moi",
                "moins", "mon", "moyennant", "même", "mêmes", "n", "ne", "ni", "non", "nos", "notre",
                "nous", "néanmoins", "nôtre", "nôtres", "on", "ont", "ou", "outre", "où", "par", "parmi",
                "partant", "pas", "passé", "pendant", "plein", "plus", "plusieurs", "pour", "pourquoi",
                "proche", "près", "puisque", "qu", "quand", "que", "quel", "quelle", "quelles", "quels",
                "qui", "quoi", "quoique", "revoici", "revoilà", "s", "sa", "sans", "sauf", "se", "selon",
                "seront", "ses", "si", "sien", "sienne", "siennes", "siens", "sinon", "soi", "soit",
                "son", "sont", "sous", "suivant", "sur", "ta", "te", "tes", "tien", "tienne", "tiennes",
                "tiens", "toi", "ton", "tous", "tout", "toute", "toutes", "tu", "un", "une", "va", "vers",
                "voici", "voilà", "vos", "votre", "vous", "vu", "vôtre", "vôtres", "y", "à", "ça", "ès",
                "été", "être", "ô");
//
//        CreateIndexResponse responseAnalyser = esClient.indices().create(
//                i-> i.index("voyage")
//                        .settings(
//                                s->s.analysis(
//                                        a->a.filter("french_stop",sd->sd.definition(ff->ff.stop(sw->sw.stopwords(stopWords)))).analyzer("rebuilt_french",
//                                                f->f.custom(v->v.tokenizer("standard").filter("lowercase","french_stop")))
//                                )
//                        )
//                        .mappings(
//                        m->m.properties(propertyMap)
//                        ));
//        Product product = new Product("bk-1", "Vélo de montagne", 123.0,"Le vélo de montagne enduro électrique SANTA CRUZ Heckler 9 2022 n'a pas pour but de rendre les choses faciles, mais de les rendre possibles. Il est muni d'un moteur Shimano et d'une batterie de 720 Wh pour une puissance accrue.");
//        Product product = new Product("Mo-1", "Souris Gamer", 123.0,"Travaillez librement avec la souris Logitech M190. Sans fil, elle vous permet de vous déplacer librement jusqu'à 10 mètres sans délai ni interférences. De plus, sa forme, de taille standard, est adaptée à la main droite comme à la main gauche et permet de positionner sa main confortablement. Et avec un capteur optique de 1000 dpi de la souris M190 vous garantit un contrôle réactif et fluide du pointeur, permettant un suivi précis et une sélection de texte aisée.");


//        SearchDocument searchDocument = new SearchDocument(
//                "1",
//                "Un séjour actif durant vos vacances La montagne pour terrain de jeu",
//                "",
//                "",
//                "",
//                "",
//                ""
//        );




//        IndexResponse responseadd = esClient.index(i -> i
//                .index("french_analyzer")
//                .id(product.getSku())
//                .document(product)
//        );

//        AnalyzeRequest analyzeRequest = analyzeRequestBuilder.index("french_analyzer").text("je veux savoir s'il est possible de voir les cacade d'ouzode à partir d'un bateau").build();
//        AnalyzeResponse analyzeResponse = esClient.indices().analyze(
//                analyzeRequest
//        );
        AnalyzeRequest.Builder analyzeRequestBuilder = new AnalyzeRequest.Builder();

        TokenFilter.Builder tokenfb = new TokenFilter.Builder();
        AnalyzeRequest analyzeRequest = analyzeRequestBuilder.analyzer("french").text("je cherche un bon vélo pour traverser les montagnes du maroc").build();
        AnalyzeResponse analyzeResponse = esClient.indices().analyze(
                analyzeRequest
        );

        String quote ="";
        for(AnalyzeToken tok : analyzeResponse.tokens())
        {
            quote=quote+" "+tok.token();
        }
        ///////////////////////////////////////////////////////////////////////////////////

//        String quote = "journée au belle cascade d'ouzoud";
//        for(AnalyzeToken tok: analyzeResponse.tokens())
//        {
//            quote=quote+" "+tok.token();
//        }
//        System.out.println(quote);
//
//
        String finalQuote = quote;
        System.out.println(finalQuote);
//        SearchResponse<SearchDocument> response = esClient.search(s -> s
//                        .index("voyages")
//                        .query(q -> q
//                                .match(t -> t
//                                        .field("description")
//                                        .query(finalQuote)
//                                )
//                        ),
//                SearchDocument.class
//        );
        SearchResponse<Product> response = esClient.search(s -> s
                        .index("french_analyzer")
                        .query(q -> q
                                .match(t -> t
                                        .field("description")
                                        .query(finalQuote)
                                )
                        ),
                Product.class
        );
//
//
//        for(Hit h:response.hits().hits())
//        {
//            SearchDocument s = (SearchDocument) h.source();
//            System.out.println("/////////////////////////////////////////////");
//            System.out.println("City");
//            System.out.println(s.getCity());
//            System.out.println("Description");
//            System.out.println(s.getDescription());
//            System.out.println("Score");
//            System.out.println(h.score());
//
//        }

        for(Hit h:response.hits().hits())
        {
            Product s = (Product) h.source();
            System.out.println("/////////////////////////////////////////////");
            System.out.println("name");
            System.out.println(s.getName());
            System.out.println("Description");
            System.out.println(s.getDescription());
            System.out.println("Score");
            System.out.println(h.score());

        }
}}
