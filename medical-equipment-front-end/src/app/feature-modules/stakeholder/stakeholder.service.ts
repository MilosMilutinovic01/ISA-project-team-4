import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
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
}