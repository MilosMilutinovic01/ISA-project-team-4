import * as L from 'leaflet';
import 'leaflet-routing-machine';
import { BehaviorSubject } from 'rxjs';
import { Component, AfterViewInit, OnInit } from '@angular/core';
import { MapService } from './map.service';
import { SimulatorService } from './simulator.service';
import { Coordinates } from '../model/coordinates.model';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';
import { User } from 'src/app/infrastructure/auth/model/user.model';
import { TokenStorage } from 'src/app/infrastructure/auth/jwt/token.service';
import { HttpClient } from '@angular/common/http';
import { Stomp } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css'],
})
export class MapComponent implements AfterViewInit, OnInit {
  private stompClient: any;
  private markers: L.Marker[] = [];
  private map: any;
  private routeControl: L.Routing.Control | undefined;
  refreshRate: number = 0;
  private coordinates: BehaviorSubject<Coordinates> =
    new BehaviorSubject<Coordinates>(
      new (class implements Coordinates {
        lat: number = 0;
        lng: number = 0;
      })()
    );
  companyCoords: Coordinates = {
    lat: 45.242092,
    lng: 19.849171,
  };
  vanCoords: Coordinates = {
    lat: 45.242292,
    lng: 19.839271,
  };
  allCoordinates: Coordinates[] = [];

  DefaultIcon = L.icon({
    iconUrl: 'https://unpkg.com/leaflet@1.6.0/dist/images/marker-icon.png',
    iconSize: [32, 32],
    iconAnchor: [16, 32],
  });

  VanIcon = L.icon({
    iconUrl: 'assets/van.png',
    iconSize: [32, 32],
    iconAnchor: [16, 32],
  });

  constructor(
    private mapService: MapService,
    private simulatorService: SimulatorService,
    private authService: AuthService,
    private tokenStorage: TokenStorage,
    private http: HttpClient
  ) {
    this.initWSConnection();
  }

  initWSConnection() {
    let jwtToken = this.tokenStorage.getAccessToken();
    let headers = {
      Authorization: jwtToken,
    };

    let socket = new SockJS('http://localhost:8090/ws');
    this.stompClient = Stomp.over(socket);

    this.stompClient.connect(
      headers,
      (frame: any) => {
        console.log('WebSocket connection successful:', frame);
        this.stompClient.subscribe('/user/queue/simulator', (message: any) => {
          let newCoordinates: Coordinates = JSON.parse(message.body);
          this.coordinates.next(newCoordinates);
          this.vanCoords = this.coordinates.getValue();
          this.setRoute();
        });
      },
      (error: any) => {
        console.error('WebSocket connection error:', error);
      }
    );
  }

  ngOnInit(): void {
    this.simulatorService.getCompanyCoords('-1').subscribe({
      next: (result) => {
        this.companyCoords = result;
      },
      error: (error) => {
        console.error('error:', error);
      },
    });
  }

  private initMap(): void {
    this.map = L.map('map', {
      center: [45.2396, 19.8227],
      zoom: 13,
    });

    const tiles = L.tileLayer(
      'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
      {
        maxZoom: 18,
        minZoom: 3,
        attribution:
          '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
      }
    );
    tiles.addTo(this.map);
    this.setRoute();
  }

  setRoute(): void {
    this.routeControl?.remove();
    this.markers.forEach((marker) => marker.removeFrom(this.map));
    this.markers = [];

    this.routeControl = L.Routing.control({
      waypoints: [L.latLng(this.companyCoords), L.latLng(this.vanCoords)],
      router: L.routing.mapbox(
        'pk.eyJ1IjoibWlraWNhMzIxNCIsImEiOiJjbHJ0YnRwc3UwNHI0Mm9wYjFzMXd3cjI3In0.S5sABCyjR4nfULHWYV_X2w',
        { profile: 'mapbox/driving-traffic' }
      ),
    }).addTo(this.map);

    this.routeControl.on('routesfound', (e) => {
      var routes = e.routes;
      var summary = routes[0].summary;
      this.allCoordinates = routes[0].coordinates;
    });

    let companyMarker = L.marker(this.companyCoords, {
      icon: this.DefaultIcon,
    }).addTo(this.map);
    let vanMarker = L.marker(this.vanCoords, { icon: this.VanIcon }).addTo(
      this.map
    );

    this.markers = [companyMarker, vanMarker];
  }

  ngAfterViewInit(): void {
    this.initMap();
  }

  startSimulator() {
    var user: User | undefined;
    this.authService.user$.subscribe((userr) => {
      user = userr;
    });
    this.simulatorService
      .startSimulator(
        this.allCoordinates.reverse(),
        user?.username ?? '',
        this.refreshRate.toString()
      )
      .subscribe((result) => {});
  }
}
