
import { AfterViewInit, Component , ElementRef, NgZone, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { FilterComponent } from '../filter/filter.component';
import { HttpClient } from '@angular/common/http';





@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements AfterViewInit{
  constructor(private zone:NgZone,public dialog: MatDialog,private httpClient: HttpClient){
  }
  @ViewChild('searchInput')
  searchInput!: ElementRef;


  ngAfterViewInit(): void {
    const inputEl = document.getElementById('search-input')
    console.log(inputEl)
    this.searchQuery = this.searchInput.nativeElement.value
  }
  searchQuery: string = '';
  isExpanded: boolean = false;

  results:any[] = []
  expandContainer() {
    console.log("expandContainer");
    this.isExpanded = true;
  }
  undoExpand() {
    if(this.searchQuery) return
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
  isError = false;
  search() {
    if(!this.searchQuery){
      this.isError = true;
      setTimeout(()=>this.isError=false,2000)
    }
    const apiUrl = `http://localhost:8090/query/${this.searchQuery}`;

    this.httpClient.get(apiUrl).subscribe(
      (response: any) => {
        this.results = response;
        console.log('Search results:', this.results);
      },
      (error) => {
        console.error('Error fetching search results:', error);
      }
    );
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
