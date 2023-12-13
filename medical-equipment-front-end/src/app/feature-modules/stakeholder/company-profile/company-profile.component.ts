import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { StakeholderService } from '../stakeholder.service';
import { Company } from '../../../shared/model/company.model';
import { Address } from '../../../shared/model/address.model';
import { MatDialog } from '@angular/material/dialog';
import { CompanyAdministartorRegistrationComponent } from '../company-administartor-registration/company-administartor-registration.component';
import { EquipmentTracking } from 'src/app/shared/model/equipmentTracking.model';
import { CompanyAdministrator } from 'src/app/shared/model/company-administrator.model';

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

  otherAdministrators: CompanyAdministrator[] = [];
  equipmentTrackings: EquipmentTracking[] = [];
  filteredEquipmentTrackings: EquipmentTracking[] = [];

  constructor(
    private service: StakeholderService,
    private dialog: MatDialog,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getCompany();
    this.getAllEquipmentTrackings();
    this.getAllCompanyAdministrators();
  }

  getCompany(): void {
    this.service.getCompanyProfile('1').subscribe({
      next: (result) => {
        this.company = result;
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
    const dialogRef = this.dialog
      .open(CompanyAdministartorRegistrationComponent, {
        width: '50%',
        height: '100%',
        data: { compId: this.company.id },
      })
      .afterClosed()
      .subscribe((result) => {
        this.otherAdministrators = [];
        this.getAllCompanyAdministrators();
      });
  }

  getAllEquipmentTrackings(): void {
    this.service.getAllEquipmentTrackings().subscribe({
      next: (result) => {
        this.equipmentTrackings = result;
        this.sort();
      },
      error: () => {
        console.log(console.error);
      },
    });
  }

  sort(): void {
    for (let el of this.equipmentTrackings) {
      if (el.company.id === this.company.id && el.count > 0) {
        this.filteredEquipmentTrackings.push(el);
      }
    }
  }

  getAllCompanyAdministrators(): void {
    this.service.getAllCompanyAdministrators().subscribe({
      next: (result) => {
        for (let a of result) {
          if (a.companyId === this.company.id && a.id !== -1) {
            this.otherAdministrators.push(a);
          }
        }
      },
      error: () => {
        console.log(console.error);
      },
    });
  }
}
