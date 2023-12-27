import { Component, Inject } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';



@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css'],

})
export class FilterComponent {
  minBudget!: number  ;
  maxBudget!: number;
  cities: string[] = [
    "Casablanca",
    "Rabat",
    "Marrakech",
    "Fès",
    "Tanger",
    "Agadir",
    "Meknès",
    "Oujda",
    "Kenitra",
    "Salé",
    "Nador",
    "Tétouan",
    "Kénitra",
    "Béni Mellal",
    "Safi",
    "Mohammedia",
    "Khouribga",
    "El Jadida",
    "Taza",
    "Essaouira",
    "Errachidia",
    "Laâyoune",
    "Dakhla",
    "Tiznit",
    "Taroudant",
    "Ksar El Kebir",
    "Guelmim",
    "Témara",
    "Berkane",
    "Khouribga",
    "Taourirt",
    "Larache",
    "Khemisset",
    "Taounate",
    "Ouarzazate",
    "Sidi Slimane",
    "Azrou"
  ] ;
  selectedCity!: string ;

  activities = [
    { name: 'Piscine', selected: false },
    { name: 'Surfing', selected: false },
    { name: 'Camping ', selected: false },
    { name: 'Plage', selected: false },
    { name: 'Football', selected: false },
    { name: 'Tennis', selected: false },
    { name: 'Handball', selected: false },
    { name: 'Aquapark', selected: false },
    { name: 'Discothèque', selected: false },



  ];
  selectedActivities: any[] = [];

  onActivitySelectionChange(activity: any) {
    if (activity.selected) {
      // Add to the selectedActivities list
      this.selectedActivities.push(activity.name);
      console.log(this.selectedActivities);
    } else {
      // Remove from the selectedActivities list
      this.selectedActivities = this.selectedActivities.filter(selectedActivity => selectedActivity !== activity.name);
      console.log(this.selectedActivities);

    }
  }


  constructor(
    public dialogRef: MatDialogRef<FilterComponent>,
    @Inject(MatDialog) public data: any
  ) {    
  }

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onApplyClick(): void {
    console.log('Min Budget:', this.minBudget);
    console.log('Max Budget:', this.maxBudget);
    console.log('Selected City:', this.selectedCity);
    console.log('Selected Activities:', this.selectedActivities);


    this.dialogRef.close({
      minBudget: this.minBudget,
      maxBudget: this.maxBudget,
      selectedCity: this.selectedCity,
      selectedActivities: this.selectedActivities
    });
  }

}
