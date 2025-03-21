import { Component, inject, OnInit } from '@angular/core';
import { InfostoreService } from '../infostore.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-confirmation',
  standalone: false,
  templateUrl: './confirmation.component.html',
  styleUrl: './confirmation.component.css'
})
export class ConfirmationComponent implements OnInit{
  private infoSvc = inject(InfostoreService)
  router = inject(Router)
  // TODO: Task 5

  date!: Date
  orderId!: string
  paymentId!: string
  total!: number




ngOnInit(): void {
  this.date = this.infoSvc.Date
  this.orderId = this.infoSvc.OrderId
  this.paymentId = this.infoSvc.PaymentId
  this.total = this.infoSvc.currentCartSubtotal
  
}

backToHomePage(){
  this.router.navigate(['/'])
}





}
