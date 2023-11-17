import { Component, EventEmitter, Inject, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { StakeholderService } from '../stakeholder.service';
import { CreateCompanyModel } from '../model/create-company.model';
@Component({
  selector: 'app-company-registration',
  templateUrl: './company-registration.component.html',
  styleUrls: ['./company-registration.component.css']
})

export class CompanyRegistrationComponent {

  constructor(private service: StakeholderService) {}
  
  companyForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    address: new FormControl('', [Validators.required]),
    city: new FormControl('', [Validators.required]),
    country: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required]),
    startTime: new FormControl('', [Validators.required]),
    endTime: new FormControl('', [Validators.required]),
  });
  
  registerCompany(): void {
    const company: CreateCompanyModel = {
      name: this.companyForm.value.name || '',
      address: this.companyForm.value.address || '',
      city: this.companyForm.value.city || '',
      country: this.companyForm.value.country || '',
      description: this.companyForm.value.description || '',
      startTime: this.companyForm.value.startTime || '',
      endTime: this.companyForm.value.endTime || '',
    };
    if (
      this.companyForm.valid) {
      this.service.registerCompany(company).subscribe({
        next: () => {
          alert('Succesfully created!');
        },
      });
    } 
  }  
}
