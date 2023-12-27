package com.example.sri_voyage_backend.Controllers;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.example.sri_voyage_backend.Entities.SearchDocument;
import com.example.sri_voyage_backend.Services.ElasticClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
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

    @GetMapping("/addDocs")
    public List<SearchDocument> addDocs(){
        List<SearchDocument> searchDocumentList=new ArrayList<>();
        searchDocumentList=List.of(
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
                        .build(),
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
