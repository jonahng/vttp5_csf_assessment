import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { menuItem, Order, ServerReceipt } from './models';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class InfostoreService {
  private http = inject(HttpClient)
  currentCart!: menuItem[]
  currentCartSubtotal!: number
  Date!: Date
  OrderId!: string
  PaymentId!: string




  constructor() { }

  getMenuItems(): Observable<menuItem[]> {
    return this.http.get<menuItem[]>(`/api/menu`)
  }


  checkout(order: Order){

    return this.http.post<ServerReceipt>(`/api/food_order`, order)

  }
}
