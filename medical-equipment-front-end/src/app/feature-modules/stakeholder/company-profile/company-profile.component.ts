import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { StakeholderService } from '../stakeholder.service';
import { Company } from '../../../shared/model/company.model';
import { Address } from '../../../shared/model/address.model';
import { MatDialog } from '@angular/material/dialog';
import { CompanyAdministartorRegistrationComponent } from '../company-administartor-registration/company-administartor-registration.component';

@Component({
  selector: 'app-company-profile',
  templateUrl: './company-profile.component.html',
  styleUrls: ['./company-profile.component.css'],
})
export class CompanyProfileComponent {
  company: Company = {
    id: NaN,
    name: '',
    address: { id: NaN, street: '', city: '', country: '' },
    description: '',
    startTime: '',
    endTime: '',
    averageRating: NaN,
  };
  companyId: string = '';

  constructor(
    private service: StakeholderService,
    private dialog: MatDialog,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getCompany();
  }

  getCompany(): void {
    this.companyId = '4';
    this.service.getCompanyProfile(this.companyId).subscribe({
      next: (result) => {
        this.company = result;
        console.log(this.company);
      },
      error: () => {
        console.log(console.error);
      },
    });
  }

  editProfile(): void {
    this.router.navigate(['/editCompanyProfile']);
  }

  addCompanyAdministrator(): void {
    const dialogRef = this.dialog.open(
      CompanyAdministartorRegistrationComponent,
      {
        width: '50%',
        height: '100%',
        data: { compId: this.company.id },
      }
    );
  }
}
