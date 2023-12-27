package com.example.sri_voyage_backend.Controllers;

import com.example.sri_voyage_backend.Entities.SearchDocument;
import com.example.sri_voyage_backend.Entities.SearchFilter;
import com.example.sri_voyage_backend.Services.ElasticClient;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200/")
public class ElasticSearchController {
    ElasticClient elasticClient;
    public ElasticSearchController(ElasticClient elasticClient  ){
        this.elasticClient=elasticClient;
    }

    @PostMapping("/query/{searchtext}")
    public List<SearchDocument> query(@PathVariable String searchtext,@RequestBody SearchFilter searchFilter){

        System.out.println(searchFilter);

        return this.elasticClient.queryDocumentPost(searchtext,searchFilter);
    }
    @GetMapping("/query/{searchtext}")
    public List<SearchDocument> query(@PathVariable String searchtext){


        return this.elasticClient.queryDocumentGet(searchtext);
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
                        .build(),
                new SearchDocument().builder()
                        .id("009")
                        .type("webpage")
                        .name("Club Framissima Les Idrissides Aqua Parc")
                        .city("Marrakech")
                        .price(3700D)
//                        .duration(String.valueOf(0))
                        .activities(List.of("Salle de sport","Tennis de Table","Piscine","Piscine Couverte","Aquapark"))
                        .description("De beaux paysages, un accueil chaleureux, une cuisine au top et des animations pour des vacances inoubliables, c'est l'engagement de nos clubs Framisssima ! ")
                        .link("https://www.promovacances.com/hotel-framissima-les-idrissides-aqua-parc/maroc-marrakech-48953.html#809441")
                        .build(),
                new SearchDocument().builder()
                        .id("010")
                        .type("webpage")
                        .name("Club Jumbo Kenzi Europa")
                        .city("Marrakech")
                        .price(3900D)
//                        .duration(String.valueOf(0))
                        .activities(List.of("Tennis","Plage","Salle de Sport","Hammam","Sauna","Basket Ball","Football","Handball","Piscine","Centre de Bien-Etre"))
                        .description("Des vacances décontractées avec du sport, de la culture, de la détente ou du farniente, faites vos jeux ! Les vacances club en famille, entre amis, à prix malin ! ")
                        .link("https://www.promovacances.com/hotel-jumbo-kenzi-europa/maroc-agadir-57301.html#1267117")
                        .build(),
                new SearchDocument().builder()
                        .id("011")
                        .type("image")
                        .name("Les vacances toniques L'Atlantique comme aire de jeu")
                        .city("Agadir")
//                        .price(3900D)
//                        .duration(String.valueOf(0))
                        .imageLink("https://www.visitmorocco.com/sites/default/files/thumbnails/image/Catamaran_0.jpg")
                        .description("Bordée par l'océan, toute l'année baignée de soleil, Agadir est la capitale du tourisme balnéaire marocain. Sa situation privilégiée, ses nombreux hôtels et centres de loisirs, leurs infrastructures modernes et variés sont autant d'opportunités de vous dépenser. Saisissez-les et adonnez-vous à votre sport favori au milieu de superbes paysages!\n" +
                                "La ville fait la part belle aux activités nautiques. L'Atlantique, battu par les vents, est un terrain de jeu parfait pour les surfeurs. Amateurs de glisse, vous vous plairez à profiter des vagues de Taghazout, à l'origine un petit village de pêcheurs à quelques kilomètres d'Agadir et qui aujourd'hui comporte une station balnéaire aux standards internationaux. Dans ses environs, \"Killer Point\" et \"Anchor point\" sont des spots que prisent les surfeurs les plus expérimentés.\n" +
                                "Dans la baie d'Agadir proprement dite, les eaux sont plus calmes. Les enfants peuvent y batifoler en toute sécurité, les nageurs tranquillement s'aventurer au large. Sur les plages, les activités sont variées : sortie en jet-ski, voile, parachute ascensionnel vous donnent l'occasion d'assouvir votre soif de sensations fortes !\n" +
                                "Entre les eaux tranquilles de la baie ou celles, plus tumultueuses, de Taghazout, l'Atlantique des environs d'Agadir vous réserve son lot de loisirs et d'émotions !\n")
                        .link("https://www.visitmorocco.com/fr/voyage/agadir-taghazout")
                        .build(),
                new SearchDocument().builder()
                        .id("012")
                        .type("image")
                        .name("visiter agadir la ville de la baie")
                        .city("Agadir")
//                        .price(3900D)
//                        .duration(String.valueOf(0))
                        .imageLink("https://levoyageducalao.com/wp-content/uploads/2023/05/plage-chameaux-agadir-maroc-1536x1152.jpg")
                        .description("Fort de son statut de première station balnéaire du Maroc, Agadir est connue pour ses plages de sable fin, ses vagues déferlantes et ses hôtels-club. Je vous avoue que c’était bien l’image que j’en avais avant d’y passer une semaine. Raison pour laquelle d’ailleurs je n’étais pas particulièrement attirée par cette destination de tourisme de masse balnéaire.\n" +
                                "Mais comme souvent, cette ville touristique est bien plus que l’image qu’elle renvoie si on se donne la peine de sortir de son hôtel, partir à l’aventure et aller à la rencontre des Berbères qui la peuplent.\n" +
                                "Ce voyage à Agadir était un peu atypique pour moi. Je ne suis pas une adepte des voyages sédentaires, je suis plutôt accroc au road-trip mais pour une fois, j’ai « lâché du lest » car ma petite famille avait envie et besoin de se poser et ne pas vivre au rythme infernal de ma soif de découvertes. Nous avons donc séjourné pendant 10 jours dans le même hôtel. Incroyable mais vrai, mes garçons n’y croyaient pas quand je leur ai annoncé la nouvelle \n" +
                                "Évidemment, ces 10 jours n’ont pas uniquement été consacré à du farniente sur un transat (dans la mesure où je tiens à peu près 10 minutes sans rien faire …). Nous avons alterné moment de détente et visite à un rythme tranquille qui nous a permis d’apprécier Agadir dans toute son entièreté. Un voyage en mode slow-tourism, aussi particulièrement adapté aux familles avec des enfants en bas âge\n")
                        .link("https://levoyageducalao.com/afrique/visiter-agadir-la-ville-de-la-baie/")
                        .build(),
                new SearchDocument().builder()
                        .id("013")
                        .type("image")
                        .name("Nos 2 idées de voyage « Taroudant »")
                        .city("Taroudant")
//                        .price(3900D)
//                        .duration(String.valueOf(0))
                        .imageLink("https://photo.comptoir.fr/asset/mot-cle/949/taroudant-maroc-662611-1440x670.jpg")
                        .description("Surnommée la \"petite Marrakech\", Taroudant est encerclée de remparts en pisé à l’intérieur desquels se trouvent une médina et des souks très animés. Historique et authentique, Taroudant est une cité chaleureuse très appréciée pour son artisanat lors d’un voyage dans le Sud marocain. En plus des objets berbères et des bijoux en argent, la ville est aussi connue pour ses tanneurs, réputés pour leur habileté. Outre les traditionnelles peaux de mouton et de chèvres, celles d’animaux plus rares, comme le renard et le dromadaire, y sont aussi travaillées.")
                        .link("https://www.comptoirdesvoyages.fr/voyage-tag/taroudant/949")
                        .build(),
                new SearchDocument().builder()
                        .id("014")
                        .type("image")
                        .name("Guide voyage Taroudant")
                        .city("Taroudant")
//                        .price(3900D)
//                        .duration(String.valueOf(0))
                        .imageLink("https://media.routard.com/image/38/7/pt91567.1300387.w630.jpg")
                        .description("Taroudant, à 80 km d’Agadir, est une ville pittoresque, protégée par de superbes remparts de couleur ocre qui valent presque à eux seuls le déplacement. Cette ancienne capitale du Sous possède aussi une petite médina et des souks modestes mais intéressants. Un peu d’artisanat, même s’il n’a pas ici la créativité de celui de Marrakech. Taroudant recèle également une kasbah au nord-est des remparts, en face de jolis jardins. \n")
                        .link("https://www.routard.com/guide_voyage_lieu/4337-taroudant.htm")
                        .build(),
                new SearchDocument().builder()
                        .id("015")
                        .type("image")
                        .name("Voyage Chefchaouen")
                        .city("Chefchaouen")
//                        .price(3900D)
//                        .duration(String.valueOf(0))
                        .imageLink("https://www.marguerite-et-troubadour.fr/wp-content/uploads/2016/11/P1140853.jpg")
                        .description("Je vous retrouve aujourd’hui pour vous raconter l’avant-dernière étape de mon road-trip au Maroc. Après Marrakech et Fès, en ce septième jour de voyage, Celia et moi mettons le cap sur Chefchaouen. Chefchaouen, ou Chaouen – c’est son p’tit nom – est une ville pas plus grande qu’un mouchoir de poche. Elle se situe au Nord du pays, à deux heures de Tanger, tranquillement installée à flanc de montagne. Mais si Chefchaouen est aussi réputée, c’est pour sa Médina bleue, mélange d’influences hispaniques, juives et arabes.")
                        .link("https://www.marguerite-et-troubadour.fr/voyage-chefchaouen/")
                        .build(),
                new SearchDocument().builder()
                        .id("016")
                        .type("image")
                        .name("Chefchaouen")
                        .city("Chefchaouen")
//                        .price(3900D)
//                        .duration(String.valueOf(0))
                        .imageLink("https://www.petitfute.com/medias/mag/21527/900_600/139061-les-incontournables-de-tanger.jpg")
                        .description("A deux heures 30 de route au sud est de Tanger, Chefchaouen ou « Chaouen » pour les intimes se niche à 600 m d’altitude sur les contreforts glorieux des montagnes du Rif. Ce grand village pittoresque, magnétique et mystérieux, inscrit au Patrimoine immatériel de l’humanité de l’UNESCO, abrite l’une des médinas les plus charmantes du Maroc réputée pour son architecture céruléenne, avec ses maisons et ses commerces aux multiples teintes bleutées. Se perdre dans ses ruelles étroites d'une grande propreté est un réel plaisir. On ne peut s’empêcher au détour d'un virage de prendre en photo les jolies portes de maisons recouvertes du célèbre pigment bleu que l'on retrouve au marché et qui suscite la joie des aquarellistes. Dans ses nombreuses boutiques, on trouve un artisanat local de qualité, des vêtements de laine, des couvertures tissées et des tapis à un prix raisonnable. Chefchaouen est aussi connue pour son fromage de chèvre local. Située sur la place de Outa El-Hamman, l'impressionnante Kasbah, ocre vestige d’un passé révolu, est une invitation à laisser défiler les heures.\n")
                        .link("https://www.petitfute.com/v46100-chefchaouen-91000/")
                        .build(),
                new SearchDocument().builder()
                        .id("017")
                        .type("webpage")
                        .name("Séjour à Essaouira")
                        .city("Essaouira")
//                        .price(3900D)
//                        .duration(String.valueOf(0))
                        .description("C’est dans un univers de calme et de sérénité que le cœur des traditions marocaines vous invite à passer des vacances de rêve sous un climat chaleureux. Essaouira est une ville où vous prendrez plaisir à découvrir ses souks animés et sa célèbre médina, classée au patrimoine mondial de l’UNESCO depuis 2001. C’est également le lieu de prédilection des amateurs de farniente qui pourront se prélasser sur la belle plage nichée au fond de la baie. Cette étape incontournable à l’ouest de Marrakech vous séduira aussi avec son port où les poissons sont vendus à la criée ou grillés sur place.")
                        .link("https://www.voyage-prive.com/offres/sejour-a-essaouira")
                        .build(),
                new SearchDocument().builder()
                        .id("018")
                        .type("webpage")
                        .name("Voyage Ouarzazate : Vacances et séjour Ouarzazate")
                        .city("Ouarzazate")
                        .activities(List.of("Excursion"))
//                        .price(3900D)
//                        .duration(String.valueOf(0))
                        .description("Départ en 4X4 vers le Haut Atlas. Passage par le col Tizi N'Tichka (2 260 m) et continuation vers Telouet. Visite de la kasbah aux riches décors intérieurs. Déjeuner. Continuation vers le ksar d'Aït Ben Haddou, ensemble de bâtiments de terre entourés de murailles, exemple frappant de l'architecture du sud marocain traditionnel. Déjeuner typique. Visite de la kasbah de Taourirt, autrefois résidence du pacha de Marrakech. Dîner et nuit à Ouarzazate.")
                        .link("https://www.fram.fr/circuit-boucles-sahariennes-en-4x4-circuit-prive-50759.html#918021")
                        .build(),
                new SearchDocument().builder()
                        .id("019")
                        .type("webpage")
                        .name("Club Framissima Royal Tafoukt Agadir Resort & Spa")
                        .city("Agadir")
                        .price(4700D)
//                        .duration(String.valueOf(0))
                        .activities(List.of("Plage","Salle de Sport","Tennis de Table","Piscine","Spa","Centre de Bien-Etre","Discothèque"))
                        .description("Départ en 4X4 vers le Haut Atlas. Passage par le col Tizi N'Tichka (2 260 m) et continuation vers Telouet. Visite de la kasbah aux riches décors intérieurs. Déjeuner. Continuation vers le ksar d'Aït Ben Haddou, ensemble de bâtiments de terre entourés de murailles, exemple frappant de l'architecture du sud marocain traditionnel. Déjeuner typique. Visite de la kasbah de Taourirt, autrefois résidence du pacha de Marrakech. Dîner et nuit à Ouarzazate.")
                        .link("https://www.promovacances.com/hotel-framissima-royal-tafoukt-agadir-resort-spa/maroc-agadir-53099.html#1001445")
                        .build(),
                new SearchDocument().builder()
                        .id("020")
                        .type("webpage")
                        .name("Week-End Asilah - Tanger À Seulement 860 DHs| Plusieurs Départs Disponibles")
                        .city("Tanger")
                        .price(860D)
//                        .duration(String.valueOf(0))
                        .description("Départ de Casablanca devant la gare « Casa voyageurs »\n" +
                                "\n" +
                                "- Départ de Rabat devant la gare « Rabat ville »\n" +
                                "\n" +
                                "- Petit déjeuner libre en route.\n" +
                                "\n" +
                                "- Arrivée à Asilah.\n" +
                                "\n" +
                                "- Visite de la ville Asilah qui est devenue avec le temps un carrefour d'artistes et d’écrivains. Vous aurez le temps de faire la visite guidée de la médina avec ses maisons peinte blanc-bleu où on remarque le mélange des influences romaines, espagnoles, portugaises et mauresques.\n" +
                                "\n" +
                                "- Déjeuner libre à Oued Tahadart, un site naturel entouré d’eaux cristallines et de falaises richement colorées. Les vagues sont populaires auprès des surfeurs, qui viennent profiter de la chaude mer Méditerranée. Tahadart est également propice à la baignade et offre un endroit idéal pour une excursion d’une demi-journée et un barbecue.\n" +
                                "\n" +
                                "- Continuation vers Tanger\n" +
                                "\n" +
                                "- Installation à l'hôtel ( Douche et détente )\n" +
                                "\n" +
                                "- Départ pour la visite de Rmilat.\n" +
                                "\n" +
                                "- Puis Visite Cap Spartel Majestueux; Le point de partage des deux étendues d'eau.\n" +
                                "\n" +
                                "- Visite de la plage Achakar, la plus célèbre des nombreuses plages de Tanger offrant un panorama unique.\n" +
                                "\n" +
                                "- Visite de la grotte d'hercules où le bleu de l'océan atlantique et le ciel forment un contraste unique\n" +
                                "\n" +
                                "- Dîner libre et nuitée à l'hôtel.")
                        .link("http://www.ajinsafro.ma/voyage-national/weekend-tanger-asilah-plusieurs-departs-disponibles-66")
                        .build(),
                new SearchDocument().builder()
                        .id("021")
                        .type("webpage")
                        .name("Week-End à Akchour - Chefchaouen - Fnideq - M'diq | Départ Chaque Vendredi Soir !")
                        .city("Akchour")
//                        .price(860D)
//                        .duration(String.valueOf(0))
                        .description("Le voyage débute avec des départs depuis Casablanca et Rabat. Le premier jour, explorez Akchour, dégustez un petit-déjeuner en pleine nature, puis partez en randonnée dans la vallée de Talembote. Après un déjeuner libre, dirigez-vous vers Chefchaouen pour une visite guidée des lieux emblématiques. La journée se termine à l'hôtel avec du temps libre. Le deuxième jour commence par un petit-déjeuner inclus, suivi d'une visite à Fnideq pour du shopping au souk et une pause café en bord de mer à M'diq. Après un déjeuner libre, le voyage se conclut avec le retour à la maison.")
                        .link("http://www.ajinsafro.ma/voyage-national/weekend-tanger-asilah-plusieurs-departs-disponibles-66")
                        .build(),
                new SearchDocument().builder()
                        .id("022")
                        .type("image")
                        .name("Comment visiter Akchour et le Pont de Dieu : une perle cachée de Chefchaouen !")
                        .city("Akchour")
//                        .price(860D)
//                        .duration(String.valueOf(0))
                        .description("Un voyage à Akchour - le Pont de Dieu et les grandes et petites cascades - est incontournable si vous prévoyez de rester deux jours ou plus à Chefchaouen et que vous recherchez une ou deux perles cachées.\n" +
                                "\n" +
                                "À seulement 45 minutes de Chefchaouen, les montagnes du Rif vous attendent... c'est le genre de destination que vous visitez si vous aimez les voyages hors des sentiers battus. Parfait pour une excursion d'une journée ou un séjour d'une nuit, une visite des eaux claires et des restaurants au bord de la rivière à Akchour et du Pont de Dieu est une aventure montagnarde marocaine à ne pas manquer !\n" +
                                "\n" +
                                "Nous avions entendu parler de quelques choses et vu quelques photos en explorant les activités à faire à Chefchaouen. Mais avant de décider nous-mêmes de nous aventurer dans les vallées et les rivières d'Akchour, nous voulions plus d'informations. Cependant, il semblait que les détails précis étaient difficiles à obtenir.")
                        .imageLink("https://www.myfreerangefamily.com/wp-content/uploads/2022/11/Akchour-Gods-Bridge-Chefchaouen-River-Picnic-Tables-3.jpg")
                        .link("https://www.myfreerangefamily.com/gods-bridge-akchour-chefchaouen/")
                        .build(),
                new SearchDocument().builder()
                        .id("023")
                        .type("video")
                        .name("370- SOS tourisme de masse !! Safi, loin de tout ça ! #truck #maroc #famillenombreuse")
                        .city("Safi")
//                        .price(860D)
//                        .duration(String.valueOf(0))
                        .description("Dans cette vidéo, nous vous emmenons découvrir Safi, cette ville qui ne subit pas encore le tourisme de masse et qui est très agréable. Vous voulez nous aider dans l'aventure ou dans notre association humainitaire, vous pouvez le faire via paypal ou via notre boutique en ligne. ")
                        .link("https://www.youtube.com/watch?v=LWiCmsZQMRE&pp=ygULdm95YWdlIHNhZmk%3D")
                        .build(),
                new SearchDocument().builder()
                        .id("024")
                        .type("video")
                        .name("122-visite de Safi et Oualidia (vanlife Maroc en camping car )")
                        .city("Safi")
//                        .price(860D)
//                        .duration(String.valueOf(0))
                        .activities(List.of("Camping "))
                        .description("A bord de notre camping car Baraka nous visitons les villes de Safi et Oualidia au Maroc. - Safi connue pour ses poteries,découvrez pourquoi elle est inscrite au livre Guinness des records. - Oualidia connue pour sa magnifique lagune et tous les amateurs d'huîtres et plateau de fruits de mer. notre site web : https://barakavanlife.company.site/ nous avons écrit un guide de conseils, santé , carte sim, argent, présentation des différentes autorités... d'astuces, a faire ,a ne pas faire...pour préparer et faciliter votre voyage au Maroc")
                        .link("https://www.youtube.com/watch?v=zgWiV31YayQ")
                        .build(),
                new SearchDocument().builder()
                        .id("025")
                        .type("image")
                        .name("Que faire lors d’un voyage à Tétouan, Maroc?")
                        .city("Tétouan")
//                        .price(860D)
//                        .duration(String.valueOf(0))
                        .description("Tétouan, également connue sous le nom de « colombe blanche », est une belle ville du nord du Maroc située sur les pentes des montagnes du Rif. La ville est devenue une destination touristique populaire pour ses charmantes rues étroites aux maisons blanchies à la chaux, bordées de marchés et d’échoppes d’artisanat et où l’on trouve des portes voûtées colorées, décorées de carreaux géométriques. En outre, il y a beaucoup d’autres lieux d’intérêt à voir à Tétouan, comme l’Ensanche, la place Hassan II ou la place Mulay Mehdi.\n" +
                                "Une journée suffit amplement pour découvrir la ville qui, si elle n’a pas autant à offrir que Marrakech ou Casablanca, n’en reste pas moins une destination attachante où vous serez surpris par la gentillesse de ses habitants. Chez Passporter, nous avons décidé de vous aider à organiser votre voyage à Tétouan, c’est pourquoi nous avons préparé ce billet avec toutes les informations dont vous avez besoin pour profiter de votre visite. Nous vous indiquons ce qu’il faut voir, comment s’y rendre, où manger et où loger et bien d’autres conseils qui vous aideront pendant votre séjour.\n")
                        .imageLink("https://passporterapp.com/fr/blog/wp-content/uploads/2023/09/que-faire-a-Tetouan.webp")
                        .link("https://passporterapp.com/fr/blog/maroc/que-faire-voyage-tetouan/")
                        .build(),
                new SearchDocument().builder()
                        .id("026")
                        .type("image")
                        .name("Week-end à Belyounech - Tétouan - Martil - M'diq - Fnideq | Plusieurs Départs Disponibles")
                        .city("Tétouan")
//                        .price(860D)
//                        .duration(String.valueOf(0))
                        .description("Découvrez la richesse du nord du Maroc lors d'un week-end fascinant à Belyounech, Tétouan, Martil, M'diq et Fnideq. Profitez de notre offre complète qui inclut un transport touristique climatisé, un hébergement confortable dans un hôtel 3 étoiles avec petit-déjeuner en chambre double ou triple, un accompagnement professionnel tout au long du séjour, et une assurance du transport pour une tranquillité d'esprit totale.\n" +
                                "Les départs sont garantis chaque vendredi soir, avec la flexibilité offerte par plusieurs départs, à condition d'avoir un minimum de 12 personnes. Réservez dès maintenant pour une escapade mémorable, explorant des destinations magnifiques, embrassant la culture locale et créant des souvenirs inoubliables dans le nord enchanteur du Maroc.\n")
                        .imageLink("https://www.ajinsafro.ma/static/team/2017/0531/14962628315848.jpg")
                        .link("http://www.ajinsafro.ma/voyage-national/weekend-a-belyounech-tetouan-martil-mdiq-fnideq-199")
                        .build(),
                new SearchDocument().builder()
                        .id("027")
                        .type("video")
                        .name("Voyage au Maroc | Visite de la magnifique ville d’Al Hoceima")
                        .city("Hoceima")
//                        .price(860D)
//                        .duration(String.valueOf(0))
                        .description("Viens découvrir avec moi la magnifique ville d’Al Hoceima")
                        .link("https://www.youtube.com/watch?v=dN_h7SekMSc")
                        .build(),
                new SearchDocument().builder()
                        .id("028")
                        .type("webpage")
                        .name("Un guide complet du surf à Taghazout")
                        .city("Taghazout")
                        .activities(List.of("Surfing"))
//                        .price(860D)
//                        .duration(String.valueOf(0))
                        .description("Surfer à Taghazout au Maroc est une expérience inoubliable. Ce charmant village côtier, situé juste au nord d'Agadir, est béni par des vagues constantes et un climat idéal toute l'année.\n" +
                                "\n" +
                                "Que vous soyez un surfeur chevronné ou un débutant, Taghazout propose une large gamme de spots de surf adaptés à tous les niveaux d'expertise. Avec des habitants accueillants, une cuisine délicieuse et une atmosphère décontractée, il n'est pas étonnant que Taghazout soit devenu un havre pour les amateurs de surf du monde entier.\n" +
                                "\n" +
                                "Alors, pourquoi ne pas préparer votre planche, enfiler une combinaison et explorer la scène du surf dans ce joyau caché sur la côte marocaine ? Vous ne le regretterez pas.")
                        .link("https://www.bestsurfdestinations.com/surfing-taghazout/")
                        .build(),
                new SearchDocument().builder()
                        .id("029")
                        .type("webpage")
                        .name("Le Parachutisme au Maroc à Beni Mellal")
                        .city("Beni Mellal")
                        .activities(List.of("Parachitisme"))
//                        .price(860D)
//                        .duration(String.valueOf(0))
                        .description("Le Saut en Tandem\n" +
                                "Le saut en Tandem est la façon la plus facile est sûre de découvrir la chute libre ; le rêve d'Icare !\n" +
                                "C'est une expérience incomparable avec tout ce que vous avez essayé avant !\n" +
                                "Accroché à un moniteur très expérimenté vous avez juste à profiter de cette sensation intense et inoubliable !\n")
                        .link("https://yallah-skydive.com/")
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
