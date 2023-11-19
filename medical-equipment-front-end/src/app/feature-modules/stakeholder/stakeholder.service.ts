import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CompanyAdministrator } from './model/company-administrator.model';
import { Company } from './model/company.model';
import { CustomerProfile } from 'src/app/infrastructure/auth/model/customer-profile.model';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/env/environment';

@Injectable({
  providedIn: 'root'
})

export class StakeholderService {
  private refreshNavbarSource = new BehaviorSubject<boolean>(false);
  refreshNavbar$ = this.refreshNavbarSource.asObservable();
  constructor(private http: HttpClient) { }
  
  getCompanyProfile(companyId: string): Observable<Company>{
    return this.http.get<Company>(environment.apiHost + "companies/profile/" + companyId);
  }

  editCompanyProfile(companyProfile : Company): Observable<Company> {
    return this.http.put<Company>(environment.apiHost + 'companies/profile/edit', companyProfile);
  }

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

  registerCompany(Company: Company): Observable<Company> {
    return this.http.post<Company>(environment.apiHost + 'companies/register', Company);
  }

  registerCompanyAdministrator(CompanyAdministrator: CompanyAdministrator): Observable<Company> {
    return this.http.post<Company>(environment.apiHost + 'companyAdministrators/register', CompanyAdministrator);
  }

  getCompanyAdministratorProfile(id : string): Observable<CompanyAdministrator> {
    return this.http.get<CompanyAdministrator>(environment.apiHost + 'companyAdministrators/profile/'+ id);
  }
}
