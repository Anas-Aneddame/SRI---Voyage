import { Component, NgZone } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {
  constructor(private zone:NgZone){}
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
  search() {
    console.log('Searching for:', this.searchQuery);
  }
  expandContainer() {
    console.log("expandContainer");
    this.isExpanded = true;
  }
  undoExpand() {
    if(this.searchQuery.length) return
    this.isExpanded = false;
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
