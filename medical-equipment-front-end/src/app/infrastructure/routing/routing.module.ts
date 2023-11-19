import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { RegistrationComponent } from '../auth/registration/registration.component';
import { HomeComponent } from 'src/app/feature-modules/layout/home/home.component';
import { CustomerProfileComponent } from 'src/app/feature-modules/stakeholder/customer-profile/customer-profile.component';
import { EditCustomerProfileComponent } from 'src/app/feature-modules/stakeholder/edit-customer-profile/edit-customer-profile.component';
import { CompanyRegistrationComponent } from 'src/app/feature-modules/stakeholder/company-registration/company-registration.component';
import { CompanyAdministartorRegistrationComponent } from 'src/app/feature-modules/stakeholder/company-administartor-registration/company-administartor-registration.component';
import { CompanyProfileComponent } from 'src/app/feature-modules/stakeholder/company-profile/company-profile.component';
import { EditCompanyProfileComponent } from 'src/app/feature-modules/stakeholder/edit-company-profile/edit-company-profile.component';
import { CompanyAdministratorProfileComponent } from 'src/app/feature-modules/stakeholder/company-administrator-profile/company-administrator-profile.component';

const routes: Routes = [
  { path: 'register', component: RegistrationComponent },
  { path: 'customerProfile', component: CustomerProfileComponent},
  { path: 'editCustomerProfile', component: EditCustomerProfileComponent},
  { path: 'companyRegistration', component: CompanyRegistrationComponent},
  { path: 'companyAdministratorRegistration', component: CompanyAdministartorRegistrationComponent},
  { path: 'companyProfile', component: CompanyProfileComponent},
  { path: 'editCompanyProfile', component: EditCompanyProfileComponent},
  { path: 'companyAdministratorProfile', component: CompanyAdministratorProfileComponent},
  { path: '', component: HomeComponent },
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class RoutingModule {}
