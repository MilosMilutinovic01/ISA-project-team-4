import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomerProfileComponent } from './customer-profile/customer-profile.component';
import { MaterialModule } from 'src/app/infrastructure/material/material.module';
import { EditCustomerProfileComponent } from './edit-customer-profile/edit-customer-profile.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CompanyRegistrationComponent } from './company-registration/company-registration.component';
import { CompaniesViewComponent } from './companies-view/companies-view.component';
@NgModule({
  declarations: [
    CustomerProfileComponent,
    EditCustomerProfileComponent,
    CompanyRegistrationComponent,
    CompaniesViewComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
  ],
  exports: [
    CustomerProfileComponent,
    CompanyRegistrationComponent
  ]
})
export class StakeholderModule { }
