import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomerProfileComponent } from './customer-profile/customer-profile.component';
import { MaterialModule } from 'src/app/infrastructure/material/material.module';
import { EditCustomerProfileComponent } from './edit-customer-profile/edit-customer-profile.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CompanyRegistrationComponent } from './company-registration/company-registration.component';
import { CompanyAdministartorRegistrationComponent } from './company-administartor-registration/company-administartor-registration.component';
import { MatDialogModule } from '@angular/material/dialog';
import { CompanyProfileComponent } from './company-profile/company-profile.component';
import { EditCompanyProfileComponent } from './edit-company-profile/edit-company-profile.component';
import { CompanyAdministratorProfileComponent } from './company-administrator-profile/company-administrator-profile.component';
import { CompaniesViewComponent } from './companies-view/companies-view.component';
import { FilterCompaniesDialogComponent } from './filter-companies-dialog/filter-companies-dialog.component';
import {MatTooltipModule} from '@angular/material/tooltip';
import { EditCompanyAdministratorProfileComponent } from './edit-company-administrator-profile/edit-company-administrator-profile.component'
import { EquipmentViewComponent } from './equipment-view/equipment-view.component'
import {MatChip, MatChipsModule} from '@angular/material/chips';
import { AppointmentRegistrationComponent } from './appointment-registration/appointment-registration.component';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatRadioModule } from '@angular/material/radio';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatInputModule } from '@angular/material/input';
import { AddToCartDialogComponent } from './add-to-cart-dialog/add-to-cart-dialog.component';
import { CartComponent } from './cart/cart.component';

@NgModule({
  declarations: [
    CustomerProfileComponent,
    EditCustomerProfileComponent,
    CompanyRegistrationComponent,
    CompaniesViewComponent,
    FilterCompaniesDialogComponent,
    CompanyAdministartorRegistrationComponent,
    CompanyProfileComponent,
    EditCompanyProfileComponent,
    CompanyAdministratorProfileComponent,
    EditCompanyAdministratorProfileComponent,
    EquipmentViewComponent,
    AppointmentRegistrationComponent
    AddToCartDialogComponent,
    CartComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule,
    MatTooltipModule,
    MatChipsModule,
    MatSelectModule,
    MatFormFieldModule,
    MatRadioModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule,
  ],
  exports: [
    CustomerProfileComponent,
    EditCustomerProfileComponent,
    CompanyRegistrationComponent,
    CompaniesViewComponent,
    FilterCompaniesDialogComponent,
    CompanyAdministartorRegistrationComponent,
    CompanyProfileComponent,
    EquipmentViewComponent,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule,
    AddToCartDialogComponent, 
    CartComponent
  ]
})
export class StakeholderModule {}
