import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CustomerProfile } from 'src/app/shared/model/customer-profile.model';
import { CompanyAdministrator } from 'src/app/shared/model/company-administrator.model';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/env/environment';
import { Company } from 'src/app/shared/model/company.model';

@Injectable({
  providedIn: 'root',
})
export class StakeholderService {
  private isRegister = new BehaviorSubject(false);
  getIsRegister = this.isRegister.asObservable();
  private refreshNavbarSource = new BehaviorSubject<boolean>(false);
  refreshNavbar$ = this.refreshNavbarSource.asObservable();
  constructor(private http: HttpClient) {}

  setIsRegister(isRegister: boolean) {
    this.isRegister.next(isRegister);
  }

  getCompanyProfile(companyId: string): Observable<Company> {
    return this.http.get<Company>(
      environment.apiHost + 'companies/profile/' + companyId
    );
  }

  editCompanyProfile(companyProfile: Company): Observable<Company> {
    console.log('U SERVISU: ');
    console.log(companyProfile);
    return this.http.put<Company>(
      environment.apiHost + 'companies/profile/edit',
      companyProfile
    );
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
}
