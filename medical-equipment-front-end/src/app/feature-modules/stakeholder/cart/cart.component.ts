import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Validators, FormBuilder } from '@angular/forms';
import { StakeholderService } from '../stakeholder.service';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';
import { ActivatedRoute, Params } from '@angular/router';
import { Item } from 'src/app/shared/model/item.model';
import { SelectAppointmentDialogComponent } from '../select-appointment-dialog/select-appointment-dialog.component';
import { Appointment } from 'src/app/shared/model/appointment.model';
import { MatStepper } from '@angular/material/stepper';
import { DxCalendarModule } from 'devextreme-angular';
import { Company } from 'src/app/shared/model/company.model';
import { SelectIrregularAppointmentDialogComponent } from '../select-irregular-appointment-dialog/select-irregular-appointment-dialog.component';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent {
  items : Item[] = [];
  totalPrice : number = 0;
  isIrregular: boolean = false;
  companyId : string = '';
  company: Company = {
    id: NaN,
    name: '',
    address: { id: NaN, street: '', city: '', country: '' },
    description: '',
    startTime: '',
    endTime: '',
    averageRating: NaN,
  };
  predefinedAppointments : Appointment[] = [];
  irregularAppointments : Appointment[] = [];
  selectedAppointment: Appointment = {
    id: NaN,
    startTime: '',
    endTime: '',
    companyAdministrator: {
      id: NaN,
      name: '',
      address: { id: NaN, street: '', city: '', country: '' },
      username: '',
      password: '',
      lastname: '',
      city: '',
      country: '',
      phoneNumber: '',
      companyId: NaN,
    },
  };

  selectedIrregularAppointment: Appointment = {
    id: NaN,
    startTime: '',
    endTime: '',
    companyAdministrator: {
      id: NaN,
      name: '',
      address: { id: NaN, street: '', city: '', country: '' },
      username: '',
      password: '',
      lastname: '',
      city: '',
      country: '',
      phoneNumber: '',
      companyId: NaN,
    },
  };
  selectedDate = new Date(Date.now());
  calendar_value: Date = new Date();
  calendar_value_irregular: Date = new Date();
  selected_appointment: Date = new Date();

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
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.companyId = params["id"];
      this.getItems();
      this.getCompany();
    });
  }

  getPredefinedDates(): void{
    this.allowedDates = [];
    this.getAppointments();
  }

  getIrregularDates(): void{
    this.allowedDates = [];
    let startDate = new Date();
    let endDate = new Date(startDate.getFullYear(),
                          startDate.getMonth() + 1,
                          startDate.getDate());

    while (startDate <= endDate) {
      this.allowedDates.push(new Date (startDate));
      startDate = new Date(startDate.getFullYear(),
                            startDate.getMonth(),
                            startDate.getDate() + 1)
    }
  }

  getItems(): void{
    this.service.getItemsByCustomerId(this.authService.getCurrentUserId().toString()).subscribe({
      next: (result) => {
        this.items = result.filter(
          i => i.company.id === Number(this.companyId)
        );

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
    this.service.getAppointments().subscribe({
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
        this.selectedAppointment = result.selectedAppointment;
      }
    });
  }

  openIrregularAppointmentsDialog(): void {
    const selectedDayOfMonth = this.calendar_value_irregular.getDate();
    const dialogRef = this.dialog.open(SelectIrregularAppointmentDialogComponent, {
      data: { 
        predefinedAppointments: this.predefinedAppointments,
        startTime : this.company.startTime,
        endTime: this.company.endTime,
        selectedDayOfMonth: selectedDayOfMonth },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        const month = this.calendar_value_irregular.getMonth() + 1;
        const start = new Date(this.calendar_value_irregular.getFullYear(),
                        month,
                        this.calendar_value_irregular.getDate(),
                        Number(result.selectedDate.split(" - ")[0].split(":")[0]),
                        Number(result.selectedDate.split(" - ")[0].split(":")[1]));

        const end = new Date(this.calendar_value_irregular.getFullYear(),
                        month,
                        this.calendar_value_irregular.getDate(),
                        Number(result.selectedDate.split(" - ")[1].split(":")[0]),
                        Number(result.selectedDate.split(" - ")[1].split(":")[1]));

        this.selectedIrregularAppointment.startTime = start.toString();
        this.selectedIrregularAppointment.endTime = end.toString();
      }
    });
  }

  finishIrregularReservation():void{
    if(this.selectedIrregularAppointment.startTime && this.selectedIrregularAppointment.endTime){
      this.service.registerIrregularAppointment(this.selectedIrregularAppointment).subscribe(
        (response) => {
          console.log('Registration successful:', response);
        },
        (error) => {
          console.error('Registration failed:', error);
        }
      );
    }
  }

  chooseIrregular() : void{
    //if(!this.selectedAppointment){
      
      this.isIrregular = true;
      this.getIrregularDates();
    //}
    // else{
    //   alert('Appointment already selected!');
    // }
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
}
