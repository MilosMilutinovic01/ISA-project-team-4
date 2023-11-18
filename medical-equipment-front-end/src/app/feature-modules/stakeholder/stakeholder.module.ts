import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomerProfileComponent } from './customer-profile/customer-profile.component';
import { MaterialModule } from 'src/app/infrastructure/material/material.module';
import { EditCustomerProfileComponent } from './edit-customer-profile/edit-customer-profile.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CompanyRegistrationComponent } from './company-registration/company-registration.component';
import { CompanyAdministartorRegistrationComponent } from './company-administartor-registration/company-administartor-registration.component';
import { MatDialogModule } from '@angular/material/dialog';
import { CompanyProfileComponent } from './company-profile/company-profile.component';
import { EditCompanyProfileComponent } from './edit-company-profile/edit-company-profile.component';

@NgModule({
  declarations: [
    CustomerProfileComponent,
    EditCustomerProfileComponent,
    CompanyRegistrationComponent,
    CompanyAdministartorRegistrationComponent,
    CompanyProfileComponent,
    EditCompanyProfileComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
    MatDialogModule
  ],
  exports: [
    CustomerProfileComponent,
    CompanyRegistrationComponent,
    CompanyAdministartorRegistrationComponent,
    CompanyProfileComponent
  ]
})
export class StakeholderModule { }
