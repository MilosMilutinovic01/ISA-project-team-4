import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreateCompanyModel } from './model/create-company.model';
import { CustomerProfile } from 'src/app/infrastructure/auth/model/customer-profile.model';
import { environment } from 'src/env/environment';

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
}
