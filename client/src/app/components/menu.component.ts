import { HttpClient } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { lastValueFrom, Observable } from 'rxjs';
import { menuItem } from '../models';
import { InfostoreService } from '../infostore.service';

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

  ngOnInit(): void {
    this.menu$=this.infoSvc.getMenuItems()
  }



}
