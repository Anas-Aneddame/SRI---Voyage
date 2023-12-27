package com.example.sri_voyage_backend.Controllers;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.example.sri_voyage_backend.Entities.SearchDocument;
import com.example.sri_voyage_backend.Services.ElasticClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ElasticSearchController {
    ElasticClient elasticClient;
    public ElasticSearchController(ElasticClient elasticClient  ){
        this.elasticClient=elasticClient;
    }

    @GetMapping("/query/{searchtext}")
    public List<SearchDocument> query(@PathVariable String searchtext){
        return this.elasticClient.queryDocument(searchtext);
    }

    @GetMapping("/addDocs")
    public List<SearchDocument> addDocs(){
        List<SearchDocument> searchDocumentList=new ArrayList<>();
        searchDocumentList=List.of(
                new SearchDocument().builder()
                        .id("004")
                        .type("video")
                        .name("Guide de voyage de Marrakech,les choses à voir absolument")
                        .city("Marrakech")
//                        .price(0D)
//                        .duration(String.valueOf(0))
                        .description("Marrakech, la ville ocre avec ses monuments historiques et ses sites culturels, est bien plus qu’un musée à ciel ouvert. Tout au long de l’année, cette ancienne capitale impériale jouit d’une atmosphère animée, à l’image de sa place Jemaa el Fna, Incontournable en soirée, ou de sa medina à l’ambiance de ville des siècles passés. Marrakech sait aussi faire rêver à travers ses nouveaux quartiers, devenus de hauts lieux de fête et de rencontre.\n" +
                                "\n" +
                                "\n" +
                                "Top 5 des choses à voir à Marrakech :\n" +
                                "\n" +
                                "•            La place Jemaa El Fna                             \n" +
                                "\n" +
                                "La place Jemaa El Fna reste l’une des places les plus réputées du Maroc. Au lever du soleil, l’endroit est d’une beauté à couper le souffle et entouré d’une aura de mystère avec ses vendeurs, ses prêcheurs et ses jeteurs de menus. De jour comme de nuit, la place est toujours animée, en raison de sa situation centrale et des bars et restaurants qui abondent sur les lieux. \n" +
                                "\n" +
                                "•            La Medina de Marrakech                        \n" +
                                "\n" +
                                "La Medina de Marrakech représente à elle seule l’âme et l’identité du Maroc. C’est l’ancienne ville qui s’est créée autour de sa place centrale, celle de Jemaa el Fna. Pour vous imprégner de l’aura de magie de la ville avant de la quitter, côtoyer les gnawas, les musiciens locaux, qui viennent tous les jours à la Medina pour chanter et danser. Prêtez-vous aux récits des conteurs de légendes et admirez le savoir-faire des dresseurs de singes et de serpents montrant leurs tours.\n" +
                                "\n" +
                                "•            Les Jardins de la Ménara                                         \n" +
                                "\n" +
                                "C’est un cadre hors du temps que dévoile les Jardins de la Ménara. Organisé autour d’un magnifique bassin, le site est une oliveraie âgée de plus de 700 ans. En ces lieux, le calme est enchanteur, propice à la relaxation. Faire une promenade au milieu des oliviers vous ramènera presque au temps où le jardin a été créé. \n" +
                                "\n" +
                                "•            La Palmeraie de Marrakech (Ben-Haddou)         \n" +
                                "\n" +
                                "L’histoire de la Palmeraie de Marrakech est intimement liée à la Medina, et à son passé de capitale impériale. Elle a été créée par le Sultan souverain du Maroc Youssef ben Tachfine comme une oasis irriguée par les nappes phréatiques de la région. Elle contient plus de 100 000 palmiers et sa visite délivre un sentiment de quiétude immense.   \n" +
                                "\n" +
                                "\n" +
                                "•            Les Cascades d’Ouzoud                                           \n" +
                                "\n" +
                                "Point de verdure en plein cœur de l’Atlas, perchées à plus de 1000m d’altitude, les cascades d’Ouzoud sont les plus belles et les plus hautes chutes d’eau du Maroc. La force de la hauteur provoque tellement de projections que l’on peut presque tout le temps y voir un arc-en-ciel.\n")
                        .link("https://www.youtube.com/watch?v=9WV35Wfwsvo")
                        .build(),
                new SearchDocument().builder()
                        .id("005")
                        .type("video")
                        .name("Une destination pour les vacances \" Tanger \"")
                        .city("Tanger")
//                        .price(0D)
//                        .duration(String.valueOf(0))
                        .description("Tanger vous propose de nombreux aménagements touristiques ainsi que de très belles plages ,idéal pour des séjours vacances...")
                        .link("https://www.youtube.com/watch?v=vGdNyxaLEpM")
                        .build(),
                new SearchDocument().builder()
                        .id("006")
                        .type("video")
                        .name("QUE FAIRE À CHEFCHAOUEN ? LES 5 MEILLEURES ACTIVITÉS - MAROC")
                        .city("Chefchaouen")
//                        .price(0D)
//                        .duration(String.valueOf(0))
                        .description("Chefchaouen, nichée au cœur des montagnes du Rif au Maroc, est une destination enchanteresse qui captive les visiteurs par ses ruelles pittoresques et ses façades bleues emblématiques. Lors de notre séjour de deux jours, nous avons exploré cette ville unique et établi notre classement des meilleures activités. Grimper au belvédère nous a offert une vue panoramique à couper le souffle, révélant la splendeur de la Médina aux teintes azurées qui s'étendent à perte de vue. Se perdre dans les dédales de la Médina était une expérience sensorielle, chaque coin révélant des nuances de bleu fascinantes. Les saveurs locales ont également conquis nos palais, avec une dégustation de spécialités locales qui a ajouté une touche gastronomique à notre séjour. Les places El Haouta et Outa El Hamam, vibrant au rythme de la vie locale, ont offert des moments authentiques et mémorables. Les chats, omniprésents et souvent câlins, ajoutent une atmosphère chaleureuse à cette ville déjà captivante. En résumé, Chefchaouen est bien plus qu'une palette de couleurs, c'est une expérience sensorielle complète où l'histoire, la culture et la beauté naturelle s'entremêlent pour créer une destination inoubliable.")
                        .link("https://www.youtube.com/watch?v=td13hAbB0SE")
                        .build(),
                new SearchDocument().builder()
                        .id("007")
                        .type("video")
                        .name("Chefchaouen la ville bleue, perle du MAROC - T. AFRIQUE ep. 1")
                        .city("Chefchaouen")
//                        .price(0D)
//                        .duration(String.valueOf(0))
                        .description("On prend doucement nos marques au Maroc et on découvre de superbes paysages et architectures. On n'imaginait pas être aussi bien reçu par les habitants de ce pays. Nous avons déjà fait de belles  rencontres. On te présente ici le travail d'un peintre avec qui nous avons sympathisé dans la ville de Chefchaouen. En plus nous te parlons ici de l'architecture et de l'histoire de cette magnifique ville bleue.")
                        .link("https://www.youtube.com/watch?v=7Ocx8mEOdpk")
                        .build(),
                new SearchDocument().builder()
                        .id("008")
                        .type("video")
                        .name("Un week-end à Marrakech")
                        .city("Marrakech")
//                        .price(0D)
//                        .duration(String.valueOf(0))
                        .description("Un week-end à Marrakech")
                        .link("https://www.youtube.com/watch?v=1t99jtpn_XU")
                        .build()
        );

        searchDocumentList.forEach(searchDocument -> {
            try {
                this.elasticClient.getElasticsearchClient().index(i -> i
                        .index("voyage")
                        .id(searchDocument.getId())
                        .document(searchDocument)
                );
            } catch (IOException e) {
                System.out.println("Problem in Indexing");
                throw new RuntimeException(e);
            }
        });

        return searchDocumentList;
    }
}
