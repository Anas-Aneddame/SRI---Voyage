import { AfterContentChecked, AfterViewChecked, AfterViewInit, ChangeDetectorRef, Component , DoCheck, ElementRef, NgZone, OnChanges, ViewChild } from '@angular/core';
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
  minBudget!: number;
  maxBudget!: number;
  selectedCity : String | undefined;
  searchQuery: string = '';
  isExpanded: boolean = false;
  filterData: any;
  isError = false;
  renderedResults:any[] = []
  areResultsRendered = false;



  @ViewChild('searchInput')
  searchInput!: ElementRef;

  docTypesIcons:any = {
    'webpage':'../../assets/site.svg',
    'image':'../../assets/image.svg',
    'video':'../../assets/video.svg',
  }

  isDocImg = (doc:any)=>doc.type==='image'


  ngAfterViewInit(): void {
    setTimeout(()=>{
      console.log(this.searchInput?.nativeElement.value);
      this.searchQuery = this.searchInput.nativeElement.value
    },500)
  }


  results:any[] = []
  expandContainer() {
    console.log("expandContainer");
    this.isExpanded = true;
  }
  undoExpand() {
    if(this.searchQuery) return
    this.isExpanded = false;
  }

  handleQueryChange(event:any){
    console.log("handleQueryChange()");
    this.areResultsRendered = false;
  }



  
    openPopup(): void {
      console.log("openPopup");
      const dialogRef = this.dialog.open(FilterComponent, {
        data: {
          
        }
      });
     
      dialogRef.afterClosed().subscribe((result:any) => {
        if(result !== undefined){
        console.log('Dialog closed with  result:', result);
        this.selectedCity = result.selectedCity;
        this.minBudget = result.minBudget;
        this.maxBudget = result.maxBudget;}
      });
    }

  search() {
    if(!this.searchQuery){
      this.isError = true;
      setTimeout(()=>this.isError=false,2000)
      return;
    }
    const searchParams = {
      minBudget: this.minBudget,
      maxBudget: this.maxBudget,
      selectedCity: this.selectedCity
    };
    const headers = {
      'Content-Type': 'application/json'
    };
    const apiUrl = `http://localhost:8090/query/${this.searchQuery}`;
    
    if (Object.values(searchParams).some(value => value !== undefined && value !== '')) {
      console.log(searchParams);
      this.httpClient.post(apiUrl, searchParams,{headers}).subscribe(
        (response: any) => {
          this.areResultsRendered = true;
          this.results = response;
          this.renderedResults = this.results;
          this.changeSelectedDocType('all');
          console.log('Search results:', this.results);
          this.minBudget = undefined!;
          this.maxBudget = undefined!;
          this.selectedCity = '';
        },
        (error) => {
          console.error('Error fetching search results:', error);
        }
        );
      } else {
        
        
        this.httpClient.get(apiUrl).subscribe(
          (response: any) => {
            this.areResultsRendered = true;
            this.results = response;
        this.renderedResults = this.results
        this.changeSelectedDocType('all')
        console.log('Search results:', this.results);

      },
      (error) => {
        console.error('Error fetching search results:', error);
      }
    );
  }}

  isQueryFilled(){
    return this.searchQuery && this.searchQuery.length >0
  }
  handleDeleteQuery(){
    this.zone.run(() => {
      this.searchQuery = "";
      this.isExpanded = false;
    });
  }

  selectedDocType:string = 'all';
  changeSelectedDocType(docType:string){
    this.selectedDocType = docType;
    if(this.selectedDocType !== 'all'){
      this.renderedResults = this.results.filter((doc:any)=>doc.type===this.selectedDocType)
    }
    else{
      this.renderedResults = this.results
    }
  }

}
