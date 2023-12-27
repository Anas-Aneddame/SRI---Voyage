
import { Component , NgZone } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { FilterComponent } from '../filter/filter.component';
import { HttpClient } from '@angular/common/http';





@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {
  constructor(private zone:NgZone,public dialog: MatDialog,private httpClient: HttpClient){}
  searchQuery: string = '';
  isExpanded: boolean = false;

  results:any[] = [
    {
      id:1,
      title:"Here are 10 reasons why you need to visit Marrakech",
      description:"We’ve provided you with a list of places to visit while you’re on your vacation to Marrakesh, a wonderful city known for it’s amazing culture and welcoming people"
    },
    {
      id:2,
      title:"Visit Marrakech now!!",
      description:"Jamaa El fna is a fabulous place to start your journey in  Morocco"
    },
    {
      id:3,
      title:"Marrakech! A city of wonders",
      description:"We would love you to visit our city"
    },
    {
      id:3,
      title:"Marrakech! A city of wonders",
      description:"We would love you to visit our city"
    },
    {
      id:3,
      title:"Marrakech! A city of wonders",
      description:"We would love you to visit our city"
    },
  ]
  expandContainer() {
    console.log("expandContainer");
    this.isExpanded = true;
  }
  undoExpand() {
    if(this.searchQuery.length) return
    this.isExpanded = false;
  }
  filterData: any;




  
    openPopup(): void {
      const dialogRef = this.dialog.open(FilterComponent, {
        data: {
          
        }
      });
    
      dialogRef.afterClosed().subscribe((result:any) => {
        console.log('Dialog closed with result:', result);
      });
    }
  search() {
    // Effectuez la requête HTTP vers votre backend Spring Boot
    const apiUrl = `http://localhost:8090/query/${this.searchQuery}`;

    this.httpClient.get(apiUrl).subscribe((response) => {
      // Traitez la réponse ici, par exemple, affichez-la dans la console
      console.log('Search results:', response);
    })
  }

  isQueryFilled(){
    return this.searchQuery && this.searchQuery.length >0
  }
  handleDeleteQuery(){
    this.zone.run(() => {
      this.searchQuery = "";
      this.isExpanded = false;
    });
  }
}
