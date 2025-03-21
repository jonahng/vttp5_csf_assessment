import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { provideHttpClient } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { MenuComponent } from './components/menu.component';
import { PlaceOrderComponent } from './components/place-order.component';

import { ConfirmationComponent } from './components/confirmation.component';
import { RouterModule, Routes } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { InfostoreService } from './infostore.service';


/* const appRoutes: Routes = [
  { path: '', component: MenuComponent },
  //{ path: 'game/:gid', component: DetailsComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full' }
] */



@NgModule({
  declarations: [
    AppComponent, MenuComponent, PlaceOrderComponent, ConfirmationComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule,AppRoutingModule
  ],
  providers: [ provideHttpClient() , InfostoreService],
  bootstrap: [AppComponent]
})
export class AppModule { }
