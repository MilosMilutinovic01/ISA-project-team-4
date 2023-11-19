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
@Component({
  selector: 'app-company-registration',
  templateUrl: './company-registration.component.html',
  styleUrls: ['./company-registration.component.css'],
})
export class CompanyRegistrationComponent {
  constructor(private service: StakeholderService) {}

  companyForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    street: new FormControl('', [Validators.required]),
    city: new FormControl('', [Validators.required]),
    country: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required]),
    startTime: new FormControl('', [Validators.required]),
    endTime: new FormControl('', [Validators.required]),
  });

  registerCompany(): void {
    const address: Address = {
      street: this.companyForm.value.street || '',
      city: this.companyForm.value.city || '',
      country: this.companyForm.value.country || '',
    };

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
          alert('Succesfully created!');
          window.location.reload();
        },
      });
    }
  }
}
