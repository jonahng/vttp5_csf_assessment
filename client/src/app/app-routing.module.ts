import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MenuComponent } from './components/menu.component';
import { PlaceOrderComponent } from './components/place-order.component';
import { ConfirmationComponent } from './components/confirmation.component';

const routes: Routes = [
  {path:"", component: MenuComponent},
  {path:"order", component: PlaceOrderComponent },
  {path:"confirm", component:ConfirmationComponent},
  {path:"**", redirectTo:"/", pathMatch:"full"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }