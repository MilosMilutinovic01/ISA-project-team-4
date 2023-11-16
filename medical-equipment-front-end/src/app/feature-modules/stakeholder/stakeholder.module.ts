import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomerProfileComponent } from './customer-profile/customer-profile.component';



@NgModule({
  declarations: [
    CustomerProfileComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    CustomerProfileComponent
  ]
})
export class StakeholderModule { }
