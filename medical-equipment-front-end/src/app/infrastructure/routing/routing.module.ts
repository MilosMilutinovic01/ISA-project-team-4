import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { RegistrationComponent } from '../auth/registration/registration.component';
import { HomeComponent } from 'src/app/feature-modules/layout/home/home.component';
import { CustomerProfileComponent } from 'src/app/feature-modules/stakeholder/customer-profile/customer-profile.component';
import { EditCustomerProfileComponent } from 'src/app/feature-modules/stakeholder/edit-customer-profile/edit-customer-profile.component';
import { CompanyRegistrationComponent } from 'src/app/feature-modules/stakeholder/company-registration/company-registration.component';
import { CompaniesViewComponent } from 'src/app/feature-modules/stakeholder/companies-view/companies-view.component';
import { CompanyProfileComponent } from 'src/app/feature-modules/stakeholder/company-profile/company-profile.component';
import { EditCompanyProfileComponent } from 'src/app/feature-modules/stakeholder/edit-company-profile/edit-company-profile.component';
import { EquipmentViewComponent } from 'src/app/feature-modules/stakeholder/equipment-view/equipment-view.component';
import { CompanyAdministratorProfileComponent } from 'src/app/feature-modules/stakeholder/company-administrator-profile/company-administrator-profile.component';
import { EditCompanyAdministratorProfileComponent } from 'src/app/feature-modules/stakeholder/edit-company-administrator-profile/edit-company-administrator-profile.component';
import { LoginComponent } from '../auth/login/login.component';
import { VerificationComponent } from '../auth/verification/verification.component';
import { CartComponent } from 'src/app/feature-modules/stakeholder/cart/cart.component';
import { CompanyCalendarComponent } from 'src/app/feature-modules/stakeholder/company-calendar/company-calendar.component';
import { AdministratorRegistrationComponent } from 'src/app/feature-modules/stakeholder/administrator-registration/administrator-registration.component';
import { ChangePasswordComponent } from 'src/app/feature-modules/stakeholder/change-password/change-password.component';
import { CustomerScheduledAppointmentsComponent } from 'src/app/feature-modules/stakeholder/customer-scheduled-appointments/customer-scheduled-appointments.component';
import { EquipmentPickupQrComponent } from 'src/app/feature-modules/stakeholder/equipment-pickup-qr/equipment-pickup-qr.component';
import { CustomerPickupHistoryComponent } from 'src/app/feature-modules/stakeholder/customer-pickup-history/customer-pickup-history.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'customerProfile', component: CustomerProfileComponent },
  { path: 'editCustomerProfile', component: EditCustomerProfileComponent },
  { path: 'companyRegistration', component: CompanyRegistrationComponent },
  { path: 'companiesView', component: CompaniesViewComponent },
  { path: 'companyProfile/:id', component: CompanyProfileComponent },
  {
    path: 'administratorRegistration/:type',
    component: AdministratorRegistrationComponent,
  },
  { path: 'editCompanyProfile', component: EditCompanyProfileComponent },
  {
    path: 'companyAdministratorProfile',
    component: CompanyAdministratorProfileComponent,
  },
  {
    path: 'editCompanyAdministratorProfile',
    component: EditCompanyAdministratorProfileComponent,
  },
  { path: 'equipment', component: EquipmentViewComponent },
  { path: '', component: HomeComponent },
  { path: 'api/auth/verify/:id', component: VerificationComponent },
  { path: 'cart/:id', component: CartComponent },
  { path: 'companyCalendar', component: CompanyCalendarComponent },
  { path: 'changePassword', component: ChangePasswordComponent },
  {
    path: 'scheduledAppointments',
    component: CustomerScheduledAppointmentsComponent,
  },
  { path: 'equipmentDeliverQR', component: EquipmentPickupQrComponent },
  { path: 'customerPickUp', component: CustomerPickupHistoryComponent },
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class RoutingModule {}
