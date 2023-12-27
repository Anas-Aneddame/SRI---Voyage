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
  cities: string[] = ['Marrakech', 'Rabat', 'Casablanca'];
  selectedCity: string = '';



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

    this.dialogRef.close({
      minBudget: this.minBudget,
      maxBudget: this.maxBudget,
      selectedCity: this.selectedCity,
    });
  }

}
