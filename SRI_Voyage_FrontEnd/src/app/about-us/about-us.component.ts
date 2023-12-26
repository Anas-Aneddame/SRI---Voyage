import { Component } from '@angular/core';

@Component({
  selector: 'app-about-us',
  templateUrl: './about-us.component.html',
  styleUrls: ['./about-us.component.css']
})
export class AboutUsComponent {
  aboutCards = [
    { number: '01', title: 'Notre mission', content: "Découvrez des destinations uniques grâce à notre plateforme intuitive. Qu'il s'agisse de plages paradisiaques, de villes dynamiques ou de joyaux cachés, votre aventure commence ici." },
    { number: '02', title: 'L\'aventure commence ici', content: "Explorez des lieux emblématiques et dénichez des trésors cachés en utilisant notre moteur de recherche convivial. Que vous soyez un voyageur chevronné ou novice, nous avons tout ce dont vous avez besoin pour planifier vos escapades." },
    { number: '03', title: 'Nos valeurs', content: "Nous croyons en la richesse de la diversité culturelle. Notre engagement envers le respect et la célébration des différences fait de chaque voyage une expérience unique. Venez découvrir le monde avec ouverture d'esprit et curiosité." },
    { number: '04', title: 'Notre communauté', content: "Rejoignez des voyageurs passionnés du monde entier. Partagez vos aventures, échangez des conseils et créez des liens avec une communauté qui partage votre amour du voyage. Ensemble, nous créons des souvenirs qui durent toute une vie." }
  ];

}
