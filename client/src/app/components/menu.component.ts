import { HttpClient } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { lastValueFrom, Observable } from 'rxjs';
import { menuItem } from '../models';
import { InfostoreService } from '../infostore.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  standalone: false,
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent implements OnInit{
  // TODO: Task 2

  private infoSvc = inject(InfostoreService)

  menu$!: Observable<menuItem[]>
  currentTotalPrice!: number

  currentCart!: menuItem[]

  router = inject(Router)




  ngOnInit(): void {
    //calls the server for json list of menu items
    this.menu$=this.infoSvc.getMenuItems()
  }


  //calculates total price by adding prices of all items in the cart
  calculateCurrentTotalPrice(){
    this.currentTotalPrice = 0
    for (let index = 0; index < this.currentCart.length; index++) {
      const element = this.currentCart[index];
      this.currentTotalPrice = this.currentTotalPrice + element.price
    }
    this.infoSvc.currentCartSubtotal = this.currentTotalPrice
  }

  addItem(m : menuItem){
    this.currentCart.push(m)

  }

  removeItem(m : menuItem){
    
  }

  placeOrder(){
    this.infoSvc.currentCart = this.currentCart;
    this.router.navigate(['/order']) //navigate to place order page


  }



}
