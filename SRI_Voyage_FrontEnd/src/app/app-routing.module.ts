import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutUsComponent } from './about-us/about-us.component';
import { SearchComponent } from './search/search.component';

const routes: Routes = [
  {path : '' , component : SearchComponent},
  {path : 'aboutUs' , component : AboutUsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
