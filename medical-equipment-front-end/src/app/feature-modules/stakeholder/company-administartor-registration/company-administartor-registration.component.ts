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
import { Company } from 'src/app/shared/model/company.model';
import { Address } from 'src/app/shared/model/address.model';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { CompanyAdministrator } from 'src/app/shared/model/company-administrator.model';

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
    const a: Address = {
      street: this.companyAdministratorForm.value.street || '',
      city: this.companyAdministratorForm.value.city || '',
      country: this.companyAdministratorForm.value.country || '',
    };

    const companyAdministrator: CompanyAdministrator = {
      name: this.companyAdministratorForm.value.name || '',
      lastname: this.companyAdministratorForm.value.lastname || '',
      address: a,
      city: this.companyAdministratorForm.value.city || '',
      country: this.companyAdministratorForm.value.country || '',
      username: this.companyAdministratorForm.value.email || '',
      phoneNumber: this.companyAdministratorForm.value.phoneNumber || '',
      companyId: this.data.compId,
    };
    console.log(companyAdministrator.companyId);
    if (this.companyAdministratorForm.valid) {
      this.service.registerCompanyAdministrator(companyAdministrator).subscribe(
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
