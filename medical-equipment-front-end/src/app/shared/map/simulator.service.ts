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
  // private stompClient: any;

  // private coordinates: BehaviorSubject<Coordinates> =
  //   new BehaviorSubject<Coordinates>(
  //     new (class implements Coordinates {
  //       lat: number = 0;
  //       lng: number = 0;
  //     })()
  //   );
  constructor(private tokenStorage: TokenStorage, private http: HttpClient) {
    // this.initWSConnection();
  }

  // initWSConnection() {
  //   let jwtToken = this.tokenStorage.getAccessToken();
  //   let headers = {
  //     Authorization: jwtToken,
  //   };

  //   let socket = new SockJS('http://localhost:8090/ws');
  //   this.stompClient = Stomp.over(socket);

  //   this.stompClient.connect(
  //     headers,
  //     (frame: any) => {
  //       console.log('WebSocket connection successful:', frame);
  //       this.stompClient.subscribe('/user/queue/simulator', (message: any) => {
  //         let newCoordinates: Coordinates = JSON.parse(message.body);
  //         this.coordinates.next(newCoordinates);
  //       });
  //     },
  //     (error: any) => {
  //       console.error('WebSocket connection error:', error);
  //     }
  //   );
  // }

  // getCoordinates() {
  //   return this.coordinates.asObservable();
  // }

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
