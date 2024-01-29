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
import { MatTooltipModule } from '@angular/material/tooltip';
import { EditCompanyAdministratorProfileComponent } from './edit-company-administrator-profile/edit-company-administrator-profile.component';
import { EquipmentViewComponent } from './equipment-view/equipment-view.component';
import { MatChip, MatChipsModule } from '@angular/material/chips';
import { CompanyCalendarComponent } from './company-calendar/company-calendar.component';
import { DxCalendarModule } from 'devextreme-angular';
import { DxSchedulerModule, DxTemplateModule } from 'devextreme-angular';
import { AdministratorRegistrationComponent } from './administrator-registration/administrator-registration.component';
import { MatDialogModule } from '@angular/material/dialog';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { AppointmentRegistrationComponent } from './appointment-registration/appointment-registration.component';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatRadioModule } from '@angular/material/radio';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatInputModule } from '@angular/material/input';
import { AddToCartDialogComponent } from './add-to-cart-dialog/add-to-cart-dialog.component';
import { CartComponent } from './cart/cart.component';
import { EquipmentRegistrationComponent } from './equipment-registration/equipment-registration.component';
import { EditEquipmentTrackingComponent } from './edit-equipment-tracking/edit-equipment-tracking.component';
import { SelectAppointmentDialogComponent } from './select-appointment-dialog/select-appointment-dialog.component';
import { SelectIrregularAppointmentDialogComponent } from './select-irregular-appointment-dialog/select-irregular-appointment-dialog.component';
import { CustomerScheduledAppointmentsComponent } from './customer-scheduled-appointments/customer-scheduled-appointments.component';
import { EquipmentPickupQrComponent } from './equipment-pickup-qr/equipment-pickup-qr.component';
import { CustomerPickupHistoryComponent } from './customer-pickup-history/customer-pickup-history.component';
import { CustomerQrCodesComponent } from './customer-qr-codes/customer-qr-codes.component';

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
    AppointmentRegistrationComponent,
    AddToCartDialogComponent,
    CartComponent,
    EquipmentRegistrationComponent,
    EditEquipmentTrackingComponent,
    CompanyCalendarComponent,
    AdministratorRegistrationComponent,
    ChangePasswordComponent,
    SelectAppointmentDialogComponent,
    SelectIrregularAppointmentDialogComponent,
    CustomerScheduledAppointmentsComponent,
    EquipmentPickupQrComponent,
    CustomerPickupHistoryComponent,
    CustomerQrCodesComponent,
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
    MatDialogModule,
    FontAwesomeModule,
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
    CompanyProfileComponent,
    EquipmentViewComponent,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule,
    AddToCartDialogComponent,
    CartComponent,
    CompanyCalendarComponent,
    DxCalendarModule,
    DxSchedulerModule,
    AdministratorRegistrationComponent,
    MatDialogModule,
    ChangePasswordComponent,
    FontAwesomeModule,
    AddToCartDialogComponent,
    CartComponent,
    SelectIrregularAppointmentDialogComponent,
    CustomerScheduledAppointmentsComponent,
    CustomerPickupHistoryComponent,
    CustomerQrCodesComponent,
  ],
})
export class StakeholderModule {}
