import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomerProfileComponent } from './customer-profile/customer-profile.component';
import { MaterialModule } from 'src/app/infrastructure/material/material.module';
import { EditCustomerProfileComponent } from './edit-customer-profile/edit-customer-profile.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CompanyRegistrationComponent } from './company-registration/company-registration.component';
import { CompanyProfileComponent } from './company-profile/company-profile.component';
import { EditCompanyProfileComponent } from './edit-company-profile/edit-company-profile.component';
import { CompanyAdministratorProfileComponent } from './company-administrator-profile/company-administrator-profile.component';
import { CompaniesViewComponent } from './companies-view/companies-view.component';
import { FilterCompaniesDialogComponent } from './filter-companies-dialog/filter-companies-dialog.component';
import {MatTooltipModule} from '@angular/material/tooltip';
import { EditCompanyAdministratorProfileComponent } from './edit-company-administrator-profile/edit-company-administrator-profile.component'
import { EquipmentViewComponent } from './equipment-view/equipment-view.component'
import {MatChip, MatChipsModule} from '@angular/material/chips';
import { CompanyCalendarComponent } from './company-calendar/company-calendar.component';
import { DxCalendarModule } from 'devextreme-angular';
import { DxSchedulerModule, DxTemplateModule } from 'devextreme-angular';
import { AdministratorRegistrationComponent } from './administrator-registration/administrator-registration.component';
@NgModule({
  declarations: [
    CustomerProfileComponent,
    EditCustomerProfileComponent,
    CompanyRegistrationComponent,
    CompaniesViewComponent,
    FilterCompaniesDialogComponent,
    CompanyProfileComponent,
    EditCompanyProfileComponent,
    CompanyAdministratorProfileComponent,
    EditCompanyAdministratorProfileComponent,
    EquipmentViewComponent,
    CompanyCalendarComponent,
    AdministratorRegistrationComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule,
    MatTooltipModule,
    MatChipsModule,
    DxCalendarModule,
    DxSchedulerModule,

  ],
  exports: [
    CustomerProfileComponent,
    EditCustomerProfileComponent,
    CompanyRegistrationComponent,
    CompaniesViewComponent,
    FilterCompaniesDialogComponent,
    CompanyProfileComponent,
    EquipmentViewComponent,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule,
    CompanyCalendarComponent,
    DxCalendarModule,
    DxSchedulerModule,
    AdministratorRegistrationComponent
  ]
})
export class StakeholderModule {}
