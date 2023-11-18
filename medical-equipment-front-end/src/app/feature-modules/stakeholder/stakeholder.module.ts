import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomerProfileComponent } from './customer-profile/customer-profile.component';
import { MaterialModule } from 'src/app/infrastructure/material/material.module';
import { EditCustomerProfileComponent } from './edit-customer-profile/edit-customer-profile.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CompanyRegistrationComponent } from './company-registration/company-registration.component';
import { CompaniesViewComponent } from './companies-view/companies-view.component';
import { FilterCompaniesDialogComponent } from './filter-companies-dialog/filter-companies-dialog.component';

@NgModule({
  declarations: [
    CustomerProfileComponent,
    EditCustomerProfileComponent,
    CompanyRegistrationComponent,
    CompaniesViewComponent,
    FilterCompaniesDialogComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule
  ],
  exports: [
    CustomerProfileComponent,
    EditCustomerProfileComponent,
    CompanyRegistrationComponent,
    CompaniesViewComponent,
    FilterCompaniesDialogComponent,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class StakeholderModule { }
