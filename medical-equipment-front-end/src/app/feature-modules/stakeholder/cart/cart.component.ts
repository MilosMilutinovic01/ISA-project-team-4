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
import { UpdateItem } from 'src/app/shared/model/update-item.model';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent {
  @ViewChild('stepper') stepper: any;

  items: Item[] = [];
  totalPrice: number = 0;
  isIrregular: boolean = false;
  predefinedAppointments: Appointment[] = [];
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
  selectedDate = new Date(Date.now());
  calendar_value: Date = new Date();
  selected_appointment: Appointment | undefined;
  isSelected: boolean = false;

  calendar_valueChanged(e: any) {
    const previousValue = e.previousValue;
    const newValue = e.value;
    this.calendar_value = newValue;
    this.openDialog();
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
      this.getItems(params);
      this.getAppointments();
    });
  }

  getItems(params: Params): void {
    this.service
      .getItemsByCustomerId(this.authService.getCurrentUserId().toString())
      .subscribe({
        next: (result) => {
          this.items = result.filter(
            (i) => i.company.id === Number(params['id'])
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
      }
    });
  }

  chooseIrregular(): void {
    this.isIrregular = true;
  }

  choosePredefined(): void {
    this.isIrregular = false;
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

      console.log('item ', i, ': ', this.items[i]);
    }

    this.service.reserveAppointment(updateItems).subscribe({
      next: (result: boolean) => {
        if (result === true) {
          console.log('Update successful');
        } else {
          console.log('Update failed');
        }
      },
      error: (error) => {
        console.log('Error during update:', error);
      },
    });
  }
}
