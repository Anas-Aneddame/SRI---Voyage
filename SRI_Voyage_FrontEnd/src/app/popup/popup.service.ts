import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PopupService {
  private filterDataSubject = new BehaviorSubject<any>(null);
  filterData$ = this.filterDataSubject.asObservable();

  setFilterData(data: any) {
    this.filterDataSubject.next(data);
  }
}