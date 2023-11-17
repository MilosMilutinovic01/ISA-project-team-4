import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CreateCompanyModel } from './model/create-company.model';
import { Observable }  from 'rxjs';
import { environment } from 'src/env/environment';

@Injectable({
  providedIn: 'root'
})

export class StakeholderService {

  constructor(private http: HttpClient) {}

  registerCompany(Company: CreateCompanyModel): Observable<CreateCompanyModel> {
    return this.http.post<CreateCompanyModel>(environment.apiHost + 'companies/register', Company);
  }

}
