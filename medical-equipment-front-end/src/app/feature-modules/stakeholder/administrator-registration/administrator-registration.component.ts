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
import { CompanyAdministrator } from 'src/app/shared/model/company-administrator.model';
import { ActivatedRoute, Router } from '@angular/router';
import { SystemAdministrator } from 'src/app/shared/model/system-administrator.model';

@Component({
  selector: 'app-administrator-registration',
  templateUrl: './administrator-registration.component.html',
  styleUrls: ['./administrator-registration.component.css']
})
export class AdministratorRegistrationComponent {
  constructor(
    private service: StakeholderService,
    public router: Router,
    private route: ActivatedRoute
  ) { }

  companies: Company[] = [];
  adminType: string = '';

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.adminType = params['type'];
    });
    this.getCompanies();
  }

  getCompanies(): void {
    this.service.getCompanies().subscribe({
      next: (result: Company[]) => {
        console.log(result);
        this.companies = result;
      },
      error: () => {
        console.log(console.error());
      },
    });
  }

  systemAdministratorForm = new FormGroup({
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
    selectedCompany: new FormControl(null, Validators.required)
  });

  registerAdministrator(): void {
    if (this.adminType == 'sa') {
      const a: Address = {
        street: this.systemAdministratorForm.value.street || '',
        city: this.systemAdministratorForm.value.city || '',
        country: this.systemAdministratorForm.value.country || '',
      };
      const systemAdministrator: SystemAdministrator = {
        name: this.systemAdministratorForm.value.name || '',
        lastname: this.systemAdministratorForm.value.lastname || '',
        address: a,
        city: this.systemAdministratorForm.value.city || '',
        country: this.systemAdministratorForm.value.country || '',
        email: this.systemAdministratorForm.value.email || '',
        phoneNumber: this.systemAdministratorForm.value.phoneNumber || '',
        hasLoggedBefore: false 
      };
      if (this.systemAdministratorForm.valid) {
        this.service.registerSystemAdministrator(systemAdministrator).subscribe(
          (response) => {
            alert('Successfully created!');
            this.router.navigate(['/']);
          },
          (error) => {
            console.error('Registration failed:', error);
          }
        );

      }
    }

    if (this.adminType == 'ca') {
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
        email: this.companyAdministratorForm.value.email || '',
        phoneNumber: this.companyAdministratorForm.value.phoneNumber || '',
        companyId: 0
      };
      if (this.companyAdministratorForm.valid) {
        var company: any = this.companyAdministratorForm.value.selectedCompany
        companyAdministrator.companyId = company.id
        this.service.registerCompanyAdministrator(companyAdministrator).subscribe(
          (response) => {
            alert('Successfully created!');
            this.router.navigate(['/']);
          },
          (error) => {
            console.error('Registration failed:', error);
          }
        );

      }

    }



  }
}
