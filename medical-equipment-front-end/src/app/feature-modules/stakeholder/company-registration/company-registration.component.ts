import {
  Component,
  EventEmitter,
  Inject,
  Input,
  OnChanges,
  OnInit,
  Output,
  SimpleChanges,
} from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { StakeholderService } from '../stakeholder.service';
import { Company } from '../../../shared/model/company.model';
import { Address } from '../../../shared/model/address.model';
import { Router } from '@angular/router';
import { MapService } from 'src/app/shared/map/map.service';
import { Coordinates } from 'src/app/shared/model/coordinates.model';
@Component({
  selector: 'app-company-registration',
  templateUrl: './company-registration.component.html',
  styleUrls: ['./company-registration.component.css'],
})
export class CompanyRegistrationComponent {
  companyCoords: Coordinates = {
    lat: NaN,
    lng: NaN,
  };
  constructor(
    private service: StakeholderService,
    private router: Router,
    private mapService: MapService
  ) {}

  companyForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    street: new FormControl('', [Validators.required]),
    city: new FormControl('', [Validators.required]),
    country: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required]),
    startTime: new FormControl('', [Validators.required]),
    endTime: new FormControl('', [Validators.required]),
  });

  search(address: string): Promise<Coordinates> {
    return new Promise((resolve, reject) => {
      this.mapService
        .search(address)
        .toPromise()
        .then((result) => {
          this.companyCoords.lat = result[0].lat;
          this.companyCoords.lng = result[0].lon;
          console.log(
            'REZULTAT PRETGRAGE ADRESE: ',
            this.companyCoords.lat,
            this.companyCoords.lng
          );
          resolve(this.companyCoords);
        })
        .catch((error) => {
          console.error('error:', error);
          reject(this.companyCoords);
        });
    });
  }

  async registerCompany(): Promise<void> {
    try {
      const address: Address = {
        street: this.companyForm.value.street || '',
        city: this.companyForm.value.city || '',
        country: this.companyForm.value.country || '',
        lat: this.companyCoords.lat,
        lng: this.companyCoords.lng,
      };

      await this.search(address.street + ', ' + address.city);
      address.lat = this.companyCoords.lat;
      address.lng = this.companyCoords.lng;

      const company: Company = {
        name: this.companyForm.value.name || '',
        address: address,
        description: this.companyForm.value.description || '',
        startTime: this.companyForm.value.startTime || '',
        endTime: this.companyForm.value.endTime || '',
      };

      if (this.companyForm.valid) {
        this.service.registerCompany(company).subscribe({
          next: () => {
            alert('Successfully created!');
            this.router.navigate(['/']);
          },
          error: (err) => {
            if (err.status === 403) {
              alert('Forbidden');
            } else {
              console.error('Error:', err);
            }
          },
        });
      }
    } catch (error) {
      console.error('Error during registration:', error);
    }
  }
}
