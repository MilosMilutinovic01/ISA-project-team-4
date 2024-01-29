import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StakeholderService } from '../stakeholder.service';
import { Company } from '../../../shared/model/company.model';
import { Address } from '../../../shared/model/address.model';
import { MatDialog } from '@angular/material/dialog';
import { EquipmentTracking } from 'src/app/shared/model/equipmentTracking.model';
import { CompanyAdministrator } from 'src/app/shared/model/company-administrator.model';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';
import { FormGroup, FormControl } from '@angular/forms';
import { AddToCartDialogComponent } from '../add-to-cart-dialog/add-to-cart-dialog.component';
import { CreateItem, Item } from 'src/app/shared/model/item.model';
import { Appointment } from 'src/app/shared/model/appointment.model';
import { User } from 'src/app/infrastructure/auth/model/user.model';
import { AppointmentRegistrationComponent } from '../appointment-registration/appointment-registration.component';
import { EquipmentRegistrationComponent } from '../equipment-registration/equipment-registration.component';
import { EquipmentType } from 'src/app/shared/model/equipment.model';
import { EditEquipmentTrackingComponent } from '../edit-equipment-tracking/edit-equipment-tracking.component';

@Component({
  selector: 'app-company-profile',
  templateUrl: './company-profile.component.html',
  styleUrls: ['./company-profile.component.css'],
})
export class CompanyProfileComponent {
  id: string = '';
  company: Company = {
    id: NaN,
    name: '',
    address: {
      id: NaN,
      street: '',
      city: '',
      country: '',
      lat: NaN,
      lng: NaN,
    },
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
  companyAdministrator: CompanyAdministrator = {
    id: NaN,
    name: '',
    address: {
      id: NaN,
      street: '',
      city: '',
      country: '',
      lat: NaN,
      lng: NaN,
    },
    username: '',
    password: '',
    lastname: '',
    city: '',
    country: '',
    phoneNumber: '',
    companyId: NaN,
  };
  equipmentCount: number = 0;
  selectedEquipmentTracking: EquipmentTracking = {
    id: NaN,
    count: 0,
    company: {
      id: NaN,
      name: '',
      address: {
        id: NaN,
        street: '',
        city: '',
        country: '',
        lat: NaN,
        lng: NaN,
      },
      startTime: '',
      endTime: '',
      description: '',
      averageRating: NaN,
    },
    equipment: {
      id: NaN,
      name: '',
      description: '',
      type: EquipmentType.DENTAL,
      price: 0,
    },
  };
  items: CreateItem[] = [];

  searchForm = new FormGroup({
    name: new FormControl(''),
  });

  constructor(
    private service: StakeholderService,
    private dialog: MatDialog,
    private router: Router,
    private authService: AuthService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.id = params['id'];
      this.authService.user$.subscribe((user) => {
        this.user = user;
      });
      this.getCompany();
      this.getAllEquipmentTrackings();
      this.getAllCompanyAdministrators();
      this.getAllAppointments();
      if (this.user?.role === 'COMPANY_ADMINISTRATOR') {
        this.getCompanyAdministratorProfile();
      }
    });
  }

  getCompany(): void {
    this.service.getCompanyProfile(this.id).subscribe({
      next: (result) => {
        this.company = result;
      },
      error: () => {
        console.log(console.error);
      },
    });
  }

  getCompanyAdministratorProfile(): void {
    this.service
      .getCompanyAdministratorProfile(this.user?.id!.toString() || '')
      .subscribe({
        next: (result) => {
          console.log('Company administrator:');
          console.log(result);
          this.companyAdministrator = result;
        },
        error: () => {
          console.log(console.error);
        },
      });
  }

  editProfile(): void {
    this.router.navigate(['/editCompanyProfile']);
  }

  getAllEquipmentTrackings(): void {
    console.log('getAllEquipmentTrackings');
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

    this.service.searchEquipmentTracking(name, this.selectedOption).subscribe({
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

    this.service.searchEquipmentTracking(name, this.selectedOption).subscribe({
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
    this.filteredEquipmentTrackings = [];
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
    this.service.getAllAppointments().subscribe({
      next: (result) => {
        this.appointments = result;
      },
      error: () => {
        console.log(console.error);
      },
    });
  }

  parseAndFormatDate(dateString: string): string {
    this.newStartDate =
      dateString[2] +
      '.' +
      dateString[1] +
      '.' +
      dateString[0] +
      '.' +
      ' ' +
      dateString[3] +
      ':' +
      dateString[4];
    if (dateString[4].toString() === '0') {
      this.newStartDate += '0';
    }
    return this.newStartDate || '';
  }

  registerNewAppointment(): void {
    const dialogRef = this.dialog
      .open(AppointmentRegistrationComponent, {
        width: '35%',
        height: '75%',
        data: { company: this.company },
      })
      .afterClosed()
      .subscribe((result) => {
        this.appointments = [];
        this.getAllAppointments();
      });
  }

  // registerAppointment(): void {
  //   this.service.registerAppointment().subscribe({
  //     next: (result: Appointment) => {
  //       //this.equipmentTracking = result;
  //       this.sort();
  //     },
  //     error: () => {
  //       console.log(console.error());
  //     },
  //   });
  // }

  addToCart(id: number, availableCount: number): void {
    const dialogRef = this.dialog
      .open(AddToCartDialogComponent, {
        width: '45%',
        height: '35%',
        data: { count: this.equipmentCount, availableCount },
      })
      .afterClosed()
      .subscribe((result) => {
        if (result) {
          const item: CreateItem = {
            count: result || '',
            customerId: this.authService.getCurrentUserId() || 0,
            companyId: this.company.id || 0,
          };

          const selectedEquipment = this.filteredEquipmentTrackings.find(
            (e) => e.id === id
          )?.equipment;
          item.equipment = selectedEquipment;

          this.service.createItem(item).subscribe({
            next: (result) => {
              this.items.push(result);
            },
            error: () => {
              console.log(console.error);
            },
          });
        }
      });
  }

  registerNewEquipment(): void {
    const dialogRef = this.dialog
      .open(EquipmentRegistrationComponent, {
        width: '37%',
        height: '66%',
        data: { compId: this.company.id },
      })
      .afterClosed()
      .subscribe((result) => {
        this.equipmentTracking = [];
        this.getAllEquipmentTrackings;
      });
  }

  deleteEquipmentTracking(e: any): void {
    console.log('delete');
    console.log(e);
    this.filteredEquipmentTrackings.forEach((element) => {
      if (element.id === e.id) {
        this.filteredEquipmentTrackings.splice(e.id, 1);
      }
    });
  }

  editEquipmentTracking(equipmentTracking: any): void {
    console.log('edit');
    console.log(equipmentTracking);
    const dialogRef = this.dialog
      .open(EditEquipmentTrackingComponent, {
        width: '39%',
        height: '72%',
        data: {
          compId: this.company.id,
          selectedEquipmentTracking: equipmentTracking,
        },
      })
      .afterClosed()
      .subscribe((result) => {
        this.equipmentTracking = [];
        this.getAllEquipmentTrackings;
        this.sort();
      });
  }

  getEquipmentTracking(id: number): EquipmentTracking {
    let equipmentTracking: any;
    this.service.getEquipmentTracking(id.toString()).subscribe({
      next: (result: EquipmentTracking) => {
        equipmentTracking = result;
      },
      error: () => {
        console.log(console.error());
      },
    });
    return equipmentTracking;
  }

  showCart(): void {
    this.router.navigate(['/cart', this.company.id]);
  }

  showCalendar(): void {
    this.router.navigate(['/companyCalendar']);
  }
}
