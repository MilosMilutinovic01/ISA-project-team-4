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
import { CompanyAdministrator } from '../model/company-administrator.model';
import { Company } from '../model/company.model';
import { Address } from '../model/address.model';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-company-administartor-registration',
  templateUrl: './company-administartor-registration.component.html',
  styleUrls: ['./company-administartor-registration.component.css'],
})
export class CompanyAdministartorRegistrationComponent {
  constructor(
    private service: StakeholderService,
    @Inject(MAT_DIALOG_DATA) public data: { compId: number },
    private dialogRef: MatDialogRef<CompanyAdministartorRegistrationComponent>
  ) {}

  companyAdministratorForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    lastname: new FormControl('', [Validators.required]),
    street: new FormControl('', [Validators.required]),
    city: new FormControl('', [Validators.required]),
    country: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    phoneNumber: new FormControl('', [
      Validators.required,
      Validators.pattern(/^\d{10}$/),
    ]),
  });

  registerCompanyAdministrator(): void {
    const companyAdministrator: CompanyAdministrator = {
      name: this.companyAdministratorForm.value.name || '',
      lastname: this.companyAdministratorForm.value.lastname || '',
      address: this.companyAdministratorForm.value.street || '',
      city: this.companyAdministratorForm.value.city || '',
      country: this.companyAdministratorForm.value.country || '',
      email: this.companyAdministratorForm.value.email || '',
      phoneNumber: this.companyAdministratorForm.value.phoneNumber || '',
      companyId: this.data.compId 
    };
    console.log(companyAdministrator.companyId)
    if (this.companyAdministratorForm.valid) {
      this.service.registerCompanyAdministrator(companyAdministrator)
        .subscribe(
          (response) => {
            console.log('Registration successful:', response);
          },
          (error) => {
            console.error('Registration failed:', error);
          }
        );
  
      this.dialogRef.close(companyAdministrator);
    }
  }
}
