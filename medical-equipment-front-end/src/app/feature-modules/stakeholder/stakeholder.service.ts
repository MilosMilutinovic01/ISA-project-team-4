import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CustomerProfile } from 'src/app/shared/model/customer-profile.model';
import { CreateCompanyModel } from './model/create-company.model';
import { environment } from 'src/env/environment';
import { Company } from 'src/app/shared/model/company.model';

@Injectable({
  providedIn: 'root'
})

export class StakeholderService {

  constructor(private http: HttpClient) { }

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

  registerCompany(Company: CreateCompanyModel): Observable<CreateCompanyModel> {
    return this.http.post<CreateCompanyModel>(environment.apiHost + 'companies/register', Company);
  }

  getCompanies(): Observable<Company[]> {
    return this.http.get<Company[]>(
      environment.apiHost + 'companies/'
    );
  }

  searchCompanies(name : string, 
                  street : string, 
                  city : string,
                  country : string): Observable<Company[]> {
    return this.http.get<Company[]>(
      environment.apiHost + 'companies/search/'+
      name + "/" +
      street + "/" + 
      city + "/" +
      country
    );
  }

  filterCompanies(rate : string, 
                  companies : Company[]): Observable<Company[]> {
    return this.http.put<Company[]>(
      environment.apiHost + 'companies/filter/'+
      rate,
      companies
    );
    }
}
