import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { menuItem } from '../models';

@Component({
  selector: 'app-menu',
  standalone: false,
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent {
  // TODO: Task 2
  constructor(private httpClient: HttpClient){}


  //getting the menu items from the server
  getMenu(){
    return lastValueFrom(this.httpClient.get<menuItem[]>("/api/menu"))
  }


}
