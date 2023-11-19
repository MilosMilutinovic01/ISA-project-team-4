import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CustomerProfile } from 'src/app/shared/model/customer-profile.model';
import { CompanyAdministrator } from 'src/app/shared/model/company-administrator.model';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/env/environment';
import { Company } from 'src/app/shared/model/company.model';
import { UpdateCompanyAdministrator } from 'src/app/shared/model/update-company-administrator.model';

@Injectable({
  providedIn: 'root',
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

  getCompany(companyId: string): Observable<Company> {
    return this.http.get<Company>(
      environment.apiHost + 'companies/companyProfile/' + companyId
    );
  }

  getCustomerProfile(id: string): Observable<CustomerProfile> {
    return this.http.get<CustomerProfile>(
      environment.apiHost + 'users/profile/' + id
    );
  }

  editCustomerProfile(profile: CustomerProfile): Observable<CustomerProfile> {
    return this.http.put<CustomerProfile>(
      environment.apiHost + 'users/profile/edit',
      profile
    );
  }

  registerCompany(Company: Company): Observable<Company> {
    return this.http.post<Company>(
      environment.apiHost + 'companies/register',
      Company
    );
  }

  registerCompanyAdministrator(
    CompanyAdministrator: CompanyAdministrator
  ): Observable<Company> {
    return this.http.post<Company>(
      environment.apiHost + 'companyAdministrators/register',
      CompanyAdministrator
    );
  }

  getCompanyAdministratorProfile(id : string): Observable<CompanyAdministrator> {
    return this.http.get<CompanyAdministrator>(environment.apiHost + 'companyAdministrators/profile/'+ id);
  }
  
  getCompanies(): Observable<Company[]> {
    return this.http.get<Company[]>(environment.apiHost + 'companies/');
  }

  searchCompanies(
    name: string,
    street: string,
    city: string,
    country: string
  ): Observable<Company[]> {
    return this.http.get<Company[]>(
      environment.apiHost +
        'companies/search/' +
        name +
        '/' +
        street +
        '/' +
        city +
        '/' +
        country
    );
  }

  filterCompanies(rate: string, companies: Company[]): Observable<Company[]> {
    return this.http.put<Company[]>(
      environment.apiHost + 'companies/filter/' + rate,
      companies
    );
  }

  editCompanyAdministratorProfile(profile : UpdateCompanyAdministrator): Observable<UpdateCompanyAdministrator> {
    return this.http.put<UpdateCompanyAdministrator>(environment.apiHost + 'companyAdministrators/profile/edit',profile);}
}
