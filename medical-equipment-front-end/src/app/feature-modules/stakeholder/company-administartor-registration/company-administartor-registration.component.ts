import { Component, EventEmitter, Inject, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { StakeholderService } from '../stakeholder.service';
import { CreateCompanyAdministratorModel } from '../model/create-company-administrator.model';
import { CreateAddressModel } from '../model/create-address.model';

@Component({
  selector: 'app-company-administartor-registration',
  templateUrl: './company-administartor-registration.component.html',
  styleUrls: ['./company-administartor-registration.component.css']
})

export class CompanyAdministartorRegistrationComponent {
  constructor(private service: StakeholderService) {}
  
  companyAdministratorForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    lastname: new FormControl('', [Validators.required]),
    street: new FormControl('', [Validators.required]),
    city: new FormControl('', [Validators.required]),
    country: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    phoneNumber: new FormControl('', [
      Validators.required,
      Validators.pattern(/^\d{10}$/),
    ]),
  });
  
  registerCompanyAdministrator(): void {
    
    // const address: CreateAddressModel = {
    //   street: this.companyForm.value.street || '',
    //   city: this.companyForm.value.city || '',
    //   country: this.companyForm.value.country || '',
    // };
    
    const companyAdministrator: CreateCompanyAdministratorModel = {
      name: this.companyAdministratorForm.value.name || '',
      lastname: this.companyAdministratorForm.value.lastname || '',
      address: this.companyAdministratorForm.value.street || '',
      city: this.companyAdministratorForm.value.city || '',
      country: this.companyAdministratorForm.value.country || '',
      password: this.companyAdministratorForm.value.password || '',
      email: this.companyAdministratorForm.value.email || '',
      phoneNumber: this.companyAdministratorForm.value.phoneNumber || '',
    };
    if (
      this.companyAdministratorForm.valid) {
      this.service.registerCompanyAdministrator(companyAdministrator).subscribe({
        next: () => {
          alert('Succesfully created!');
        },
      });
    } 
  }  
}
