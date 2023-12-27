import { Component } from '@angular/core';
import { PopupService } from '../popup/popup.service';
import { MatDialog } from '@angular/material/dialog';
import { FilterComponent } from '../filter/filter.component';




@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {
  searchQuery: string = '';
  isExpanded: boolean = false;
  search() {
    console.log('Searching for:', this.searchQuery);
  }
  expandContainer() {
    console.log("expandContainer");
    this.isExpanded = true;
  }
  undoExpand() {
    this.isExpanded = false;
  }
  filterData: any;

  constructor(public dialog: MatDialog,) {}



  
    openPopup(): void {
      const dialogRef = this.dialog.open(FilterComponent, {
        data: {
          
        }
      });
    
      dialogRef.afterClosed().subscribe((result:any) => {
        console.log('Dialog closed with result:', result);
      });
    }
}
