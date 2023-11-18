import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CompanyAdministratorModel } from './model/company-administrator.model';
import { CompanyModel } from './model/company.model';
import { CustomerProfile } from 'src/app/infrastructure/auth/model/customer-profile.model';
import { BehaviorSubject, Observable } from 'rxjs';
import { Company } from 'src/app/infrastructure/auth/model/company.model';
import { environment } from 'src/env/environment';

@Injectable({
  providedIn: 'root'
})

export class StakeholderService {
  private refreshNavbarSource = new BehaviorSubject<boolean>(false);
  refreshNavbar$ = this.refreshNavbarSource.asObservable();

  constructor(private http: HttpClient) { }

  getCompany(companyId: string): Observable<Company>{
    return this.http.get<Company>(environment.apiHost + "companies/companyProfile/" + companyId);
  }

  getCustomerProfile(id : string): Observable<CustomerProfile> {
    return this.http.get<CustomerProfile>(
      environment.apiHost + 'users/profile/'+
      id
    );
  }

  editCustomerProfile(profile : CustomerProfile): Observable<CustomerProfile> {
    return this.http.put<CustomerProfile>(
      environment.apiHost + 'users/profile/edit',
      profile
    );
  }

  registerCompany(Company: CompanyModel): Observable<CompanyModel> {
    return this.http.post<CompanyModel>(environment.apiHost + 'companies/register', Company);
  }

  registerCompanyAdministrator(CompanyAdministrator: CompanyAdministratorModel): Observable<CompanyModel> {
    return this.http.post<CompanyModel>(environment.apiHost + 'companyAdministrators/register', CompanyAdministrator);
  }
}
