import { Injectable } from '@angular/core';
import { Registration } from './model/registration.model';
import { Observable, tap } from 'rxjs';
import { RegistrationResponse } from './model/registration.response';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from 'src/env/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient, private router: Router) {}

  register(registration: Registration): Observable<RegistrationResponse> {
    return this.http.post<RegistrationResponse>(
      environment.apiHost + 'users/register',
      registration
    );
  }
}
