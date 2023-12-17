import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Validators, FormBuilder } from '@angular/forms';
import { SelectAppointmentDialogComponent } from '../select-appointment-dialog/select-appointment-dialog.component';
import { DxCalendarModule } from 'devextreme-angular';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent {
  calendar_value: Date = new Date();

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

  constructor(private _formBuilder: FormBuilder, private dialog: MatDialog) {
    const currentDate = new Date();

    for (let i = 0; i < 3; i++) {
      const newDate = new Date(
        currentDate.getFullYear(),
        currentDate.getMonth(),
        currentDate.getDate() + i
      );
      this.allowedDates.push(newDate);
    }
  }

  ngOnInit(): void {}

  isDateDisabled = (date: Date): boolean => {
    return !this.allowedDates.some((allowedDate) =>
      this.isSameDate(date, allowedDate)
    );
  };

  openDialog(): void {
    console.log('Nova vrednost: ', this.calendar_value);
    const dialogRef = this.dialog.open(SelectAppointmentDialogComponent, {
      data: { selectedDate: this.calendar_value },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.calendar_value = result.selectedDate;
        this.allowedDates = result.allowedDates || [];
      }
    });
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
