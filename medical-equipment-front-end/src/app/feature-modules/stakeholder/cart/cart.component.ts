import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Validators, FormBuilder } from '@angular/forms';
import { StakeholderService } from '../stakeholder.service';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Item } from 'src/app/shared/model/item.model';
import { SelectAppointmentDialogComponent } from '../select-appointment-dialog/select-appointment-dialog.component';
import { Appointment } from 'src/app/shared/model/appointment.model';
import { MatStepper } from '@angular/material/stepper';
import { DxCalendarModule } from 'devextreme-angular';
import { Company } from 'src/app/shared/model/company.model';
import { SelectIrregularAppointmentDialogComponent } from '../select-irregular-appointment-dialog/select-irregular-appointment-dialog.component';
import { UpdateItem } from 'src/app/shared/model/update-item.model';
import { StepperSelectionEvent } from '@angular/cdk/stepper';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent {
  items: Item[] = [];
  totalPrice: number = 0;
  isIrregular: boolean = false;
  companyId: string = '';
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
  reservedAppointments: Appointment[] = [];
  predefinedAppointments: Appointment[] = [];
  irregularAppointments: Appointment[] = [];
  selectedAppointment: Appointment = {
    id: NaN,
    startTime: '',
    endTime: '',
    isPredefined: false,
    companyAdministrator: {
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
    },
  };

  // selectedIrregularAppointment: Appointment = {
  //   id: NaN,
  //   startTime: '',
  //   endTime: '',
  //   companyAdministrator: {
  //     id: NaN,
  //     name: '',
  //     address: { id: NaN, street: '', city: '', country: '' },
  //     username: '',
  //     password: '',
  //     lastname: '',
  //     city: '',
  //     country: '',
  //     phoneNumber: '',
  //     companyId: NaN,
  //   },
  // };
  selectedDate = new Date(Date.now());
  calendar_value: Date = new Date();
  calendar_value_irregular: Date = new Date();
  selected_appointment: Appointment | undefined;
  isSelected: boolean = false;
  isSelectedIrregular: boolean = false;

  calendar_valueChanged(e: any) {
    const previousValue = e.previousValue;
    const newValue = e.value;
    this.calendar_value = newValue;
    this.openDialog();
  }

  calendar_value_irregularChanged(e: any) {
    const previousValue = e.previousValue;
    const newValue = e.value;
    this.calendar_value_irregular = newValue;
    this.openIrregularAppointmentsDialog();
  }
  allowedDates: Date[] = [];
  firstFormGroup = this._formBuilder.group({
    firstCtrl: ['', Validators.required],
  });
  secondFormGroup = this._formBuilder.group({
    secondCtrl: ['', Validators.required],
  });

  constructor(
    private _formBuilder: FormBuilder,
    private service: StakeholderService,
    private authService: AuthService,
    private route: ActivatedRoute,
    private dialog: MatDialog,
    public router: Router
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.companyId = params['id'];
      this.getItems();
      this.getCompany();
      this.getReservedAppointments();
    });
  }

  getPredefinedDates(): void {
    this.allowedDates = [];
    this.getAppointments();
  }

  getIrregularDates(): void {
    this.allowedDates = [];
    let startDate = new Date();
    let endDate = new Date(
      startDate.getFullYear(),
      startDate.getMonth() + 1,
      startDate.getDate()
    );

    while (startDate <= endDate) {
      this.allowedDates.push(new Date(startDate));
      startDate = new Date(
        startDate.getFullYear(),
        startDate.getMonth(),
        startDate.getDate() + 1
      );
    }
  }

  getItems(): void {
    this.service
      .getItemsByCustomerId(this.authService.getCurrentUserId().toString())
      .subscribe({
        next: (result) => {
          this.items = result.filter(
            (i) => i.company.id === Number(this.companyId)
          );

          this.items = this.items.filter((i) => i.appointment === null);

          for (let i of this.items) {
            this.totalPrice += i.count * Number(i.equipment?.price);
          }
        },
        error: () => {
          console.log(console.error);
        },
      });
  }

  getAppointments(): void {
    this.service.getFreeAppointmentsByCompanyId(this.companyId).subscribe({
      next: (result) => {
        this.predefinedAppointments = result;

        for (let a of result) {
          const newDate = new Date(
            parseFloat(a.startTime[0]),
            parseFloat(a.startTime[1]) - 1,
            parseFloat(a.startTime[2])
          );
          if (!this.allowedDates.includes(newDate)) {
            this.allowedDates.push(newDate);
          }
        }
      },
      error: () => {
        console.log(console.error);
      },
    });
  }

  getCompany(): void {
    this.service.getCompanyProfile(this.companyId).subscribe({
      next: (result) => {
        this.company = result;
      },
      error: () => {
        console.log(console.error);
      },
    });
  }

  calculatePrice(item: Item): number {
    return item.count * Number(item.equipment?.price);
  }

  isDateDisabled = (date: Date): boolean => {
    return !this.allowedDates.some((allowedDate) =>
      this.isSameDate(date, allowedDate)
    );
  };

  openDialog(): void {
    const selectedDayOfMonth = this.calendar_value.getDate();
    const dialogRef = this.dialog.open(SelectAppointmentDialogComponent, {
      data: {
        appointments: this.predefinedAppointments,
        selectedDayOfMonth: selectedDayOfMonth,
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.selected_appointment = result.selectedAppointment;
        this.isSelected = true;
        this.isSelectedIrregular = false;
      }
    });
  }

  getReservedAppointments(): void {
    this.service.getScheduledAppointmentsByCompanyId(this.companyId).subscribe({
      next: (result) => {
        this.reservedAppointments = result;
      },
      error: () => {
        console.log(console.error);
      },
    });
  }

  openIrregularAppointmentsDialog(): void {
    const selectedDayOfMonth = this.calendar_value_irregular.getDate();
    const dialogRef = this.dialog.open(
      SelectIrregularAppointmentDialogComponent,
      {
        data: {
          predefinedAppointments: this.predefinedAppointments,
          reservedAppointments: this.reservedAppointments,
          startTime: this.company.startTime,
          endTime: this.company.endTime,
          selectedDayOfMonth: selectedDayOfMonth,
        },
      }
    );

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        const month = this.calendar_value_irregular.getMonth();
        const start = new Date(
          this.calendar_value_irregular.getFullYear(),
          month,
          this.calendar_value_irregular.getDate(),
          Number(result.selectedDate.split(' - ')[0].split(':')[0]),
          Number(result.selectedDate.split(' - ')[0].split(':')[1])
        );

        const end = new Date(
          this.calendar_value_irregular.getFullYear(),
          month,
          this.calendar_value_irregular.getDate(),
          Number(result.selectedDate.split(' - ')[1].split(':')[0]),
          Number(result.selectedDate.split(' - ')[1].split(':')[1])
        );

        this.selectedAppointment.startTime = start.toString();
        this.selectedAppointment.endTime = end.toString();
        this.isSelected = false;
        this.isSelectedIrregular = true;
      }
    });
  }

  finishIrregularReservation(): void {
    if (
      this.selectedAppointment.startTime &&
      this.selectedAppointment.endTime
    ) {
      this.createIrregular();
    }
  }

  createIrregular(): void {
    this.selectedAppointment.isPredefined = false;
    this.service
      .registerIrregularAppointment(this.selectedAppointment)
      .subscribe(
        (response) => {
          this.selectedAppointment = response;
          console.log('Registration successful:', this.selectedAppointment);
          this.updateItemsInCart(this.selectedAppointment);
        },
        (error) => {
          console.error('Registration failed:', error);
        }
      );
  }

  updateItemsInCart(appointment: Appointment) {
    const updateItems: UpdateItem[] = [];
    console.log('APPOINTMENT TO UPDATE: ', appointment);

    for (let i = 0; i < this.items.length; i++) {
      this.items[i].appointment = appointment;

      const updateItem: UpdateItem = {
        Id: this.items[i].id || 0,
        Count: this.items[i].count,
        AppointmentId: this.items[i].appointment?.id || 0,
        CompanyId: this.items[i].company.id || 0,
        CustomerId: this.items[i].customer.id || 0,
        EquipmentId: this.items[i].equipment?.id || 0,
      };

      updateItems.push(updateItem);
    }

    this.updateItems(updateItems);
  }

  chooseIrregular(): void {
    this.isIrregular = true;
    this.getIrregularDates();
  }

  onStepperSelectionChange(event: StepperSelectionEvent): void {
    const selectedStep = event.selectedIndex;

    if (selectedStep === 1) {
      this.choosePredefined();
    }
  }

  choosePredefined(): void {
    this.isIrregular = false;
    this.getPredefinedDates();
  }
  private isSameDate(date1: Date, date2: Date): boolean {
    return (
      date1.getDate() === date2.getDate() &&
      date1.getMonth() === date2.getMonth() &&
      date1.getFullYear() === date2.getFullYear()
    );
  }

  getCellCssClass(cell: any): string {
    const date = cell.date;

    if (this.isDateAllowed(date)) {
      return 'allowed-date';
    }

    return '';
  }

  isDateAllowed(date: Date): boolean {
    return this.allowedDates.some((allowedDate) =>
      this.isSameDate(date, allowedDate)
    );
  }

  finish() {
    // Create an array of UpdateItem objects
    const updateItems: UpdateItem[] = [];
    console.log('APPOINTMENT TO UPDATE: ', this.selectedAppointment);

    for (let i = 0; i < this.items.length; i++) {
      this.items[i].appointment = this.selected_appointment;

      const updateItem: UpdateItem = {
        Id: this.items[i].id || 0,
        Count: this.items[i].count,
        AppointmentId: this.items[i].appointment?.id || 0,
        CompanyId: this.items[i].company.id || 0,
        CustomerId: this.items[i].customer.id || 0,
        EquipmentId: this.items[i].equipment?.id || 0,
      };

      updateItems.push(updateItem);
    }
    this.updateItems(updateItems);
  }

  updateItems(items: UpdateItem[]): void {
    this.service.reserveAppointment(items).subscribe({
      next: (result: boolean) => {
        if (result === true) {
          alert('Succesfully finished reservation!');
          this.router.navigate(['/companyProfile/', this.companyId]);
        } else {
          alert('Failed to finish reservationc!');
        }
      },
      error: (error) => {
        console.log('Error during update:', error);
      },
    });
  }
}
