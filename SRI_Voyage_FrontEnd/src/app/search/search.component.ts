import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { FilterComponent } from '../filter/filter.component';
import { HttpClient } from '@angular/common/http';





@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {
  searchQuery: string = '';
  filterData: any;

  constructor(public dialog: MatDialog,private httpClient: HttpClient) {}




  
    openPopup(): void {
      const dialogRef = this.dialog.open(FilterComponent, {
        data: {
          
        }
      });
    
      dialogRef.afterClosed().subscribe(result => {
        console.log('Dialog closed with result:', result);
      });
    }
  /*search() {
    console.log('Searching for:', this.searchQuery);

  }
  */
  

  search() {
    // Effectuez la requête HTTP vers votre backend Spring Boot
    const apiUrl = `http://localhost:8090/query/${this.searchQuery}`;

    this.httpClient.get(apiUrl).subscribe((response) => {
      // Traitez la réponse ici, par exemple, affichez-la dans la console
      console.log('Search results:', response);
    });
  }
}