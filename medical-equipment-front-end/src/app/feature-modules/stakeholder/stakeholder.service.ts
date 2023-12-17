import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CustomerProfile } from 'src/app/shared/model/customer-profile.model';
import { CompanyAdministrator } from 'src/app/shared/model/company-administrator.model';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/env/environment';
import { Company } from 'src/app/shared/model/company.model';
import { UpdateCompanyAdministrator } from 'src/app/shared/model/update-company-administrator.model';
import { Equipment } from 'src/app/shared/model/equipment.model';
import { EquipmentTracking } from 'src/app/shared/model/equipmentTracking.model';
import { SystemAdministrator } from 'src/app/shared/model/system-administrator.model';
import { User } from 'src/app/infrastructure/auth/model/user.model';

@Injectable({
  providedIn: 'root',
})
export class StakeholderService {
  private isRegister = new BehaviorSubject(false);
  getIsRegister = this.isRegister.asObservable();
  private refreshNavbarSource = new BehaviorSubject<boolean>(false);
  refreshNavbar$ = this.refreshNavbarSource.asObservable();
  constructor(private http: HttpClient) { }

  setIsRegister(isRegister: boolean) {
    this.isRegister.next(isRegister);
  }

  getCompanyProfile(companyId: string): Observable<Company> {
    return this.http.get<Company>(environment.apiHost + "companies/profile/" + companyId);
  }

  editCompanyProfile(companyProfile: Company): Observable<Company> {
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
    CompanyAdministrator: CompanyAdministrator): Observable<Company> {
    return this.http.post<Company>(environment.apiHost + 'companyAdministrators/register',CompanyAdministrator);
  }

  registerSystemAdministrator(
    SystemAdministrator: SystemAdministrator): Observable<Company> {
    return this.http.post<Company>(environment.apiHost + 'systemAdministrators/register',SystemAdministrator);
  }

  getCompanyAdministratorProfile(id: string): Observable<CompanyAdministrator> {
    return this.http.get<CompanyAdministrator>(environment.apiHost + 'companyAdministrators/profile/' + id);
  }

  getCompanies(): Observable<Company[]> {
    return this.http.get<Company[]>(environment.apiHost + 'companies/');
  }

  getEquipment(): Observable<Equipment[]> {
    return this.http.get<Equipment[]>(environment.apiHost + 'equipment/');
  }

  searchEquipment(name: string, type: string): Observable<EquipmentTracking[]> {
    return this.http.get<EquipmentTracking[]>(environment.apiHost + 'equipmentTracking/search/' + name +
      '/' +
      type);
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

  editCompanyAdministratorProfile(profile: UpdateCompanyAdministrator): Observable<UpdateCompanyAdministrator> {
    return this.http.put<UpdateCompanyAdministrator>(environment.apiHost + 'companyAdministrators/profile/edit', profile);
  }

  getAllEquipmentTrackings(): Observable<EquipmentTracking[]> {
    return this.http.get<EquipmentTracking[]>(environment.apiHost + 'equipmentTracking/');
  }

  getAllCompanyAdministrators(): Observable<CompanyAdministrator[]> {
    return this.http.get<CompanyAdministrator[]>(environment.apiHost + 'companyAdministrators/');
  }

  getSystemAdministrator(id: number): Observable<SystemAdministrator> {
    return this.http.get<SystemAdministrator>(environment.apiHost + 'systemAdministrators/' + id);
  }

  updateSystemAdministrator(admin: SystemAdministrator): Observable<SystemAdministrator> {
    console.log(admin);
    return this.http.put<SystemAdministrator>(
       environment.apiHost + 'systemAdministrators/changePassword', admin);
   }

}
