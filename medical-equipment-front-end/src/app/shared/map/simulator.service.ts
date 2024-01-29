import { Injectable } from '@angular/core';
import { Stomp } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import { BehaviorSubject, Observable } from 'rxjs';
import { TokenStorage } from 'src/app/infrastructure/auth/jwt/token.service';
import { Coordinates } from '../model/coordinates.model';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/env/environment';

@Injectable({
  providedIn: 'root',
})
export class SimulatorService {
  constructor(private tokenStorage: TokenStorage, private http: HttpClient) {}

  startSimulator(coordinates: any, forUser: string, refreshRate: string) {
    return this.http.post<String>(
      environment.apiHost +
        `simulator/start?user=${forUser}&refreshRate=${refreshRate}`,
      coordinates
    );
  }

  getCompanyCoords(companyId: String) {
    return this.http.get<Coordinates>(
      environment.apiHost + `companies/coordinates/${companyId}`
    );
  }

  markAsReserved(reservationId: number) {
    return this.http.get<String>(
      environment.apiHost + `reservation/finishDelivery/${reservationId}`
    );
  }
}
