import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { menuItem, Order, ServerReceipt } from '../models';
import { InfostoreService } from '../infostore.service';

@Component({
  selector: 'app-place-order',
  standalone: false,
  templateUrl: './place-order.component.html',
  styleUrl: './place-order.component.css'
})
export class PlaceOrderComponent implements OnInit{

  private fb = inject(FormBuilder)
  private infoSvc = inject(InfostoreService)

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
        this.paymentId = response.orderId;
      }
    })

  }

}
