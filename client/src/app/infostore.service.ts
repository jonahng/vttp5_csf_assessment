import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { menuItem } from './models';

@Injectable({
  providedIn: 'root'
})
export class InfostoreService {
  private http = inject(HttpClient)
  currentCart!: menuItem[]
  currentCartSubtotal!: number

  constructor() { }

  getMenuItems(): Observable<menuItem[]> {
    return this.http.get<menuItem[]>(`/api/menu`)
  }
}
