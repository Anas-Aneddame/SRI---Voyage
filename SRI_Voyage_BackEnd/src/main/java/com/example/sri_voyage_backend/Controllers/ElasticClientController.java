package com.example.sri_voyage_backend.Controllers;


import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.analysis.*;
import co.elastic.clients.elasticsearch._types.mapping.Property;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Operator;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.*;
import co.elastic.clients.elasticsearch.indices.analyze.AnalyzeToken;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import co.elastic.clients.util.ObjectBuilder;
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


        Map<String, Property> propertyMap = new HashMap<>();
        propertyMap.put("id", new Property.Builder().text(t->t.analyzer("french")).build());
        propertyMap.put("name", new Property.Builder().text(t->t.analyzer("french")).build());
        propertyMap.put("city", new Property.Builder().text(t->t.analyzer("french")).build());
        propertyMap.put("duration", new Property.Builder().text(t->t.analyzer("french")).build());
        propertyMap.put("price", new Property.Builder().text(t->t.analyzer("french")).build());
        propertyMap.put("link", new Property.Builder().text(t->t.analyzer("french")).build());
        propertyMap.put("description", new Property.Builder().text(t->t.analyzer("french")).build());
        propertyMap.put("type", new Property.Builder().text(t->t.analyzer("french")).build());

        CreateIndexResponse responseAnalyser = esClient.indices().create(
                i-> i.index("voyage")
                        .mappings(
                        m->m.properties(propertyMap)
                        ));

        List<SearchDocument> searchDocumentList=List.of(
                new SearchDocument().builder()
                        .id("001")
                        .type("webpage")
                        .name("Promenade en dromadaire, tour en quad et hammam traditionnel - Transport inclus depuis Marrakech")
                        .city("Marrakech")
                        .price(690.00)
                        .duration(String.valueOf(5))
                        .description("Profitez d’une activité complète à Marrakech en combinant balade en dromadaire, quad et Hammam traditionnel !\n" +
                                "\n" +
                                "Votre chauffeur viendra vous chercher à votre hôtel de Marrakech. Vous débuterez votre journée par une balade à dos de dromadaire dans la célèbre Palmeraie, située au nord de la ville.\n" +
                                "\n" +
                                "Vous prendrez ensuite, les commandes d'un quad et bénéficierez d’un temps d’adaptation pour maitriser la conduite.\n" +
                                "Une fois prêt, vous partirez pour un tour d’environ 1h30 lors duquel vous pourrez admirer les paysages de la Palmeraie et la ville de Marrakech au loin.\n" +
                                "\n" +
                                "Avant de regagner la ville, vous profiterez d’une pause pour déguster un thé à la menthe marocain.\n" +
                                "\n" +
                                "Pour vous remettre de ces sensations fortes, vous passerez les portes d’un hammam pour un moment de détente.\n" +
                                "Au programme : gommage traditionnel au savon noir suivi d’un massage relaxant à l’huile d’argan.\n" +
                                "\n" +
                                "Le hammam traditionnel est reconnu pour ses bienfaits purifiants, vous en ressortirez revivifié et avec une sensation de sérénité.\n" +
                                "\n" +
                                "Vous vivrez une journée d’aventures, de découverte, de détente et de traditions à Marrakech !")
                        .link("https://activites.routard.com/marrakech/promenade-en-dromadaire-tour-en-quad-et-hammam-traditionnel---transport-inclus-depuis-marrakech?c=1x0")
                        .build(),
                new SearchDocument().builder()
                        .id("002")
                        .type("webpage")
                        .name("Balade en dromadaire dans les environs d'Agadir")
                        .city("Agadir")
                        .price(250.00)
                        .description("Découvrez les alentours d’Agadir lors de cette randonnée à dos de dromadaire !\n" +
                                "\n" +
                                "Depuis votre hôtel, vous serez amené à un ranch où vous choisirez de monter à dos de dromadaire pour parcourir les environs d’Agadir lors d'une balade relaxante.\n" +
                                "\n" +
                                "Vous traverserez une forêt d’eucalyptus située à proximité du palais royal. Vous longerez également le fleuve du Oued Souss d'où vous profiterez d’une superbe vue sur des paysages naturels.\n" +
                                "\n" +
                                "A la fin de votre randonnée, vous dégusterez un thé à la menthe et quelques pâtisseries marocaines avant d'être ramené à votre hôtel.")
                        .link("https://activites.routard.com/agadir/balade-en-dromadaire-dans-les-environs-dagadir?c=2x0")
                        .build(),
                new SearchDocument().builder()
                        .id("003")
                        .type("webpage")
                        .name("Excursion d’une journée aux cascades d’Ouzoud – Au départ de Marrakech")
                        .city("Marrakech")
                        .price(190.00)
                        .description("Evadez-vous le temps d’une journée au départ de Marrakech et découvrez les magnifiques cascades d’Ouzoud !\n" +
                                "\n" +
                                "Situées à environ 150km de Marrakech, au cœur de l’Atlas et à plus de 100 mètres de hauteur, les cascades d’Ouzoud sont les plus grandes du pays.\n" +
                                "Ces chutes d’eaux sont classées parmi les plus beaux sites du Maroc.\n" +
                                "\n" +
                                "Après avoir été récupéré à votre hôtel à Marrakech, vous prendrez la route vers l’Atlas et vous parcourrez les paysages naturels vous menant jusqu’à Ouzoud.\n" +
                                "\n" +
                                "Une fois sur place, vous profiterez d’un temps libre pour vous promener parmi les oliviers. Vous vous rapprocherez de plus en plus des cascades jusqu’à vous retrouver en face. Vous pourrez alors profiter du lieu et vous détendre face à ce merveilleux paysage.\n" +
                                "\n" +
                                "Avec de la chance, vous pourriez même rencontrer de petits singes !\n" +
                                "\n" +
                                "Si vous le souhaitez, vous déjeunerez dans un restaurant à la cuisine locale situé le long de la rivière avec un point de vue magnifique sur les chutes d’eaux.\n" +
                                "\n" +
                                "Après avoir profité de cette excursion, vous serez raccompagné à votre hôtel de Marrakech en fin de journée.")
                        .link("https://activites.routard.com/marrakech/excursion-dune-journee-aux-cascades-douzoud-depart-marrakech?c=1x0")
                        .build()
        );

//        searchDocumentList.forEach(searchDocument -> {
//            try {
//                esClient.index(i -> i
//                        .index("voyage")
//                        .id(searchDocument.getId())
//                        .document(searchDocument)
//                );
//            } catch (IOException e) {
//                System.out.println("Problem in Indexing");
//                throw new RuntimeException(e);
//            }
//        });


        AnalyzeRequest.Builder analyzeRequestBuilder = new AnalyzeRequest.Builder();

        String query = "tour eiffel";
        AnalyzeRequest analyzeRequest = analyzeRequestBuilder.analyzer("french").text(query).build();
        AnalyzeResponse analyzeResponse = esClient.indices().analyze(
                analyzeRequest
        );

        String quote ="";
        for(AnalyzeToken tok : analyzeResponse.tokens())
        {
            quote=quote+" "+tok.token();
        }
//        AnalyzeRequest.Builder analyzeRequestBuilder2 = new AnalyzeRequest.Builder();
//        AnalyzeRequest analyzeRequest2 = analyzeRequestBuilder2.tokenizer(t->t.name("standard")).filter(t->t.name("ngram")).text(quote).build();
//        AnalyzeResponse analyzeResponse2 = esClient.indices().analyze(
//                analyzeRequest2
//        );
//        for(AnalyzeToken tok : analyzeResponse2.tokens())
//        {
//            quote=quote+" "+tok.token();
//        }

        ///////////////////////////////////////////////////////////////////////////////////


        String finalQuote = quote;
        System.out.println(finalQuote);

        List<Query> queryList = new ArrayList<>();
        for(AnalyzeToken tok : analyzeResponse.tokens())
        {
            queryList.add(Query.of(sh->sh.fuzzy(f->f.field("description").value(tok.token()))));
            queryList.add(Query.of(sh->sh.fuzzy(f->f.field("name").value(tok.token()))));

        }
        Query byName = MatchQuery.of(m -> m
                .field("city")
                .query("marrakech")
        )._toQuery();

        Query byMaxPrice = RangeQuery.of(r -> r
                .field("price")
                .lte(JsonData.of(190.0))
        )._toQuery();
        SearchResponse<SearchDocument> response = esClient.search(s -> s
                        .index("voyage")
                        .query(q -> q.bool(
                                b->b.should(
                                        queryList
                                ).must(byMaxPrice,byName))

                        ),
                SearchDocument.class
        );
        System.out.println(response);

        for(Hit h:response.hits().hits())
        {
            SearchDocument s = (SearchDocument) h.source();
            System.out.println("/////////////////////////////////////////////");
            System.out.println("name");
            System.out.println(s.getName());
            System.out.println("Description");
            System.out.println(s.getDescription());
            System.out.println("Price");
            System.out.println(s.getPrice());
            System.out.println("Score");

            System.out.println(h.score());

        }
}}
