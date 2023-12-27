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
  filterData: any;

  constructor(public dialog: MatDialog,) {}



  
    openPopup(): void {
      const dialogRef = this.dialog.open(FilterComponent, {
        data: {
          
        }
      });
    
      dialogRef.afterClosed().subscribe(result => {
        console.log('Dialog closed with result:', result);
      });
    }
  search() {
    console.log('Searching for:', this.searchQuery);
  }
}