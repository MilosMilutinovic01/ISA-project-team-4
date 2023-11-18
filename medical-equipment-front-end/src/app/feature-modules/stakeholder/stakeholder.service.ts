import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Company } from 'src/app/infrastructure/auth/model/company.model';
import { CustomerProfile } from 'src/app/infrastructure/auth/model/customer-profile.model';
import { environment } from 'src/env/environment';

@Injectable({
  providedIn: 'root'
})
export class StakeholderService {
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
}
