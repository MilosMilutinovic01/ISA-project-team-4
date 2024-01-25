import * as L from 'leaflet';
import { Component, AfterViewInit } from '@angular/core';
import { MapService } from './map.service';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css'],
})
export class MapComponent implements AfterViewInit {
  private map: any;
  private routeControl: L.Routing.Control | undefined;

  constructor(private mapService: MapService) {}

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
    this.registerOnClick();
    this.setRoute();
  }

  setRoute(): void {
    this.routeControl?.remove();
    console.log('AJ LEBA TI PRVI:', this.routeControl);
    var r = L.Routing.control({
      waypoints: [
        L.latLng(45.250335, 19.803955),
        // L.latLng(45.242092, 19.849171),
      ],
      router: L.routing.mapbox(
        'pk.eyJ1IjoibWlraWNhMzIxNCIsImEiOiJjbHJ0YnRwc3UwNHI0Mm9wYjFzMXd3cjI3In0.S5sABCyjR4nfULHWYV_X2w',
        {
          profile: 'mapbox/walking',
        }
      ),
    });
    console.log('AJ LEBA TI:', this.routeControl);
    r.addTo(this.map);

    r.on('routesfound', function (e) {
      var routes = e.routes;
      var summary = routes[0].summary;
      alert(
        'Total distance is ' +
          summary.totalDistance / 1000 +
          ' km and total time is ' +
          Math.round((summary.totalTime % 3600) / 60) +
          ' minutes'
      );
    });
  }

  ngAfterViewInit(): void {
    let DefaultIcon = L.icon({
      iconUrl: 'https://unpkg.com/leaflet@1.6.0/dist/images/marker-icon.png',
      iconSize: [32, 32],
      iconAnchor: [16, 32],
    });

    L.Marker.prototype.options.icon = DefaultIcon;
    this.initMap();
  }

  // search(): void {
  //   this.mapService.search('Strazilovska 19, Novi Sad').subscribe({
  //     next: (result) => {
  //       console.log(result);
  //       L.marker([result[0].lat, result[0].lon])
  //         .addTo(this.map)
  //         .bindPopup('Pozdrav iz Strazilovske 19.')
  //         .openPopup();
  //     },
  //     error: () => {},
  //   });
  // }

  registerOnClick(): void {
    this.map.on('click', (e: any) => {
      const coord = e.latlng;
      const lat = coord.lat;
      const lng = coord.lng;
      this.mapService.reverseSearch(lat, lng).subscribe((res) => {
        console.log(res.display_name);
      });
      console.log(
        'You clicked the map at latitude: ' + lat + ' and longitude: ' + lng
      );
      const mp = new L.Marker([lat, lng]).addTo(this.map);
      alert(mp.getLatLng());
    });
  }
}
