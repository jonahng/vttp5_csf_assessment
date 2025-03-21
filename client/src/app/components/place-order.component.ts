import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { menuItem, Order, ServerReceipt } from '../models';
import { InfostoreService } from '../infostore.service';
import { Router } from '@angular/router';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-place-order',
  standalone: false,
  templateUrl: './place-order.component.html',
  styleUrl: './place-order.component.css'
})
export class PlaceOrderComponent implements OnInit{

  private fb = inject(FormBuilder)
  private infoSvc = inject(InfostoreService)
  router = inject(Router)

  currentCart!: menuItem[]
  form!: FormGroup
  subtotal!: number
  paymentId!: string;
  

  

  order: Order = {
    username: '',
    password: '',
    items: []
    }

  ngOnInit(): void {
    this.form = this.createForm();
    this.subtotal = this.infoSvc.currentCartSubtotal
    this.currentCart = this.infoSvc.currentCart
  }

  // TODO: Task 3


  private createForm(): FormGroup{
    return this.fb.group({
      username: this.fb.control<string>('',Validators.required),
      password: this.fb.control<string>('',Validators.required),
      items: this.fb.control<menuItem>
    })
  }


  submitForm(){
    this.order = this.form.value
    this.infoSvc.checkout(this.order).subscribe({
      next: (response: ServerReceipt) =>{
        this.paymentId = response.paymentId;
        this.infoSvc.PaymentId=  response.paymentId;
        this.infoSvc.OrderId = response.orderId;
        this.infoSvc.Date = response.timestamp;
    
        this.router.navigate(['/confirm']) //GO TO VIEW 3
      },
      error: (error: HttpErrorResponse) =>{
        alert("ERROR WITH SUBMITTING YOUR ORDER" + error.message);
      }
    })

  }


  restart(){
    this.form = this.createForm(); //clear this form
    this.router.navigate(['/']) //GO TO VIEW 1
  }

}
