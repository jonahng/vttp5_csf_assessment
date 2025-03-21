import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-place-order',
  standalone: false,
  templateUrl: './place-order.component.html',
  styleUrl: './place-order.component.css'
})
export class PlaceOrderComponent implements OnInit{

  private fb = inject(FormBuilder)

  form!: FormGroup


  ngOnInit(): void {
    this.form = this.createForm();
  }

  // TODO: Task 3


  private createForm(): FormGroup{
    return this.fb.group({
      username: this.fb.control<string>('',Validators.required),
      password: this.fb.control<string>('',Validators.required)
    })
  }

}
