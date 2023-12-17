import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { StakeholderService } from '../stakeholder.service';
import { Company } from '../../../shared/model/company.model';
import { Address } from '../../../shared/model/address.model';
import { MatDialog } from '@angular/material/dialog';
import { CompanyAdministartorRegistrationComponent } from '../company-administartor-registration/company-administartor-registration.component';
import { EquipmentTracking } from 'src/app/shared/model/equipmentTracking.model';
import { CompanyAdministrator } from 'src/app/shared/model/company-administrator.model';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';
import { FormGroup, FormControl } from '@angular/forms';
import { User } from 'src/app/infrastructure/auth/model/user.model';
import { Appointment } from 'src/app/shared/model/appointment.model';
import { DatePipe } from '@angular/common';
import { AppointmentRegistrationComponent } from '../appointment-registration/appointment-registration.component';

@Component({
  selector: 'app-company-profile',
  templateUrl: './company-profile.component.html',
  styleUrls: ['./company-profile.component.css'],
  providers: [DatePipe]
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

  user: User | undefined;
  otherAdministrators: CompanyAdministrator[] = [];
  filteredEquipmentTrackings: EquipmentTracking[] = [];
  equipmentTracking: EquipmentTracking[] = [];
  selectedOption: string = 'empty';
  appointments: Appointment[] = [];
  displayedColumns: string[] = ['startTime', 'endTime', 'customerId'];
  newStartDate: string = '';

  searchForm = new FormGroup({
    name: new FormControl(''),
  });


  constructor(
    private service: StakeholderService,
    private dialog: MatDialog,
    private router: Router,
    private authService: AuthService,
    private datePipe: DatePipe
  ) {}

  ngOnInit(): void {
    this.authService.user$.subscribe((user) => {
      this.user = user;
    });
    this.getCompany();
    this.getAllEquipmentTrackings();
    this.getAllCompanyAdministrators();
    this.getAllAppointments();
  }
  
  getCompany(): void {
    this.service.getCompanyAdministratorProfile((this.user?.id!).toString()).subscribe({
      next: (result) => {
        this.service.getCompanyProfile(result.companyId.toString()).subscribe({
          next: (result) => {
            this.company = result;
            console.log(this.company);
          },
          error: () => {
            console.log(console.error);
          },
        });
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
    console.log('getAllEquipmentTrackings')
    this.service.getAllEquipmentTrackings().subscribe({
      next: (result) => {
        this.equipmentTracking = result;
        this.sort();
      },
      error: () => {
        console.log(console.error);
      },
    });
  }

  selectChip(type: string): void {
    this.selectedOption = type;
    const name = this.searchForm.value.name || 'empty';

    this.service.searchEquipment(name, this.selectedOption).subscribe({
      next: (result: EquipmentTracking[]) => {
        this.equipmentTracking = result;
        this.sort();
      },
      error: () => {
        console.log(console.error());
      },
    });
  }

  search(): void {
    const name = this.searchForm.value.name || 'empty';

    this.service.searchEquipment(name, this.selectedOption).subscribe({
      next: (result: EquipmentTracking[]) => {
        this.equipmentTracking = result;
        this.sort();
      },
      error: () => {
        console.log(console.error());
      },
    });
  }

  sort(): void {
    for (let el of this.equipmentTracking) {
      if (el.company.id === this.company.id && el.count > 0) {
        this.filteredEquipmentTrackings.push(el);
      }
    }
  }

  refresh(): void {
    this.equipmentTracking = [];
    this.searchForm.setValue({ name: '' });
    this.selectedOption = 'empty';
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

  getAllAppointments(): void {
    this.service.getAppointments().subscribe({
      next: (result) => {
        this.appointments = result;
      },
      error: () => {
        console.log(console.error);
      },
    });
  }
  
  parseAndFormatDate(dateString: string): string {
    this.newStartDate = dateString[2] + '.' + dateString[1] + '.' + dateString[0]+ '.' + ' ' + dateString[3] + ':' + dateString[4];
    if(dateString[4].toString() === "0"){
      this.newStartDate += '0';
    }
   return this.newStartDate || '';
  }

  registerNewAppointment(): void {
    const dialogRef = this.dialog
      .open(AppointmentRegistrationComponent, {
        width: '35%',
        height: '70%',
        data: { company: this.company}
      })
      .afterClosed()
      .subscribe((result) => {
        this.appointments = [];
        this.getAllAppointments();
      }); 
  }

/*
  registerAppointment(): void {
    this.service.registerAppointment().subscribe({
      next: (result: Appointment) => {
        this.equipmentTracking = result;
        this.sort();
      },
      error: () => {
        console.log(console.error());
      },
    });
  } 
  */

}
