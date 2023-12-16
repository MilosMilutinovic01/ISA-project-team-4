import { Injectable } from '@angular/core';
import { Registration } from './model/registration.model';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { RegistrationResponse } from './model/registration.response';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from 'src/env/environment';
import { Login } from './model/login.model';
import { User } from './model/user.model';
import { TokenStorage } from './jwt/token.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AuthenticationResponse } from './model/authentication-response.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  user$ = new BehaviorSubject<User>({
    username: '',
    id: 0,
    role: '',
  });

  constructor(
    private http: HttpClient,
    private tokenStorage: TokenStorage,
    private router: Router
  ) {}

  register(registration: Registration): Observable<RegistrationResponse> {
    return this.http.post<RegistrationResponse>(
      environment.apiHost + 'auth/customer/register',
      registration
    );
  }

  verify(id: Number): Observable<Boolean> {
    return this.http.get<Boolean>(environment.apiHost + 'auth/verify/' + id);
  }

  login(login: Login): Observable<AuthenticationResponse> {
    return this.http
      .post<AuthenticationResponse>(environment.apiHost + 'auth/login', login)
      .pipe(
        tap((authenticationResponse) => {
          this.tokenStorage.saveAccessToken(authenticationResponse.accessToken);
          this.setUser();
        })
      );
  }

  logout(): void {
    this.tokenStorage.clear();
    this.router.navigate(['']);
    this.user$.next({ username: '', id: 0, role: '' });
  }

  checkIfUserExists(): void {
    const accessToken = this.tokenStorage.getAccessToken();
    if (accessToken == null) {
      return;
    }
    this.setUser();
  }

  private setUser(): void {
    const jwtHelperService = new JwtHelperService();
    const accessToken = this.tokenStorage.getAccessToken() || '';
    const user: User = {
      id: +jwtHelperService.decodeToken(accessToken).id,
      username: jwtHelperService.decodeToken(accessToken).username,
      role: jwtHelperService.decodeToken(accessToken).role,
    };

    this.user$.next(user);
  }

  getCurrentUserId(): number {
    const jwtHelperService = new JwtHelperService();
    const accessToken = this.tokenStorage.getAccessToken() || '';
    const decodedToken = jwtHelperService.decodeToken(accessToken);

    return decodedToken.id;
  }
}
