import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { RegistrationComponent } from '../auth/registration/registration.component';
import { HomeComponent } from 'src/app/feature-modules/layout/home/home.component';
import { CustomerProfileComponent } from 'src/app/feature-modules/stakeholder/customer-profile/customer-profile.component';
import { EditCustomerProfileComponent } from 'src/app/feature-modules/stakeholder/edit-customer-profile/edit-customer-profile.component';
import { CompanyProfileComponent } from 'src/app/feature-modules/stakeholder/company-profile/company-profile.component';
import { EditCompanyProfileComponent } from 'src/app/feature-modules/stakeholder/edit-company-profile/edit-company-profile.component';

const routes: Routes = [
  { path: 'register', component: RegistrationComponent },
  { path: 'customerProfile', component: CustomerProfileComponent},
  { path: 'editCustomerProfile', component: EditCustomerProfileComponent},
  { path: 'companyProfile', component: CompanyProfileComponent},
  { path: 'editCompanyProfile', component: EditCompanyProfileComponent},
  { path: '', component: HomeComponent },
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class RoutingModule {}
