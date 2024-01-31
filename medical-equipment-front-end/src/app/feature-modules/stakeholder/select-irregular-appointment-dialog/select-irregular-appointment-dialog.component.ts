import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { CompanyProfileComponent } from '../company-profile/company-profile.component';
import { Appointment } from 'src/app/shared/model/appointment.model';
import { CartComponent } from '../cart/cart.component';
import { StakeholderService } from '../stakeholder.service';

@Component({
  selector: 'app-select-irregular-appointment-dialog',
  templateUrl: './select-irregular-appointment-dialog.component.html',
  styleUrls: ['./select-irregular-appointment-dialog.component.css'],
})
export class SelectIrregularAppointmentDialogComponent {
  availableAppointments: String[] = [];
  constructor(
    public dialogRef: MatDialogRef<CompanyProfileComponent>,
    private router: Router,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    let startStr = '2023-12-12T' + data.startTime + ':00.000Z';
    let startDate = new Date(startStr);
    let endStr = '2023-12-12T' + data.endTime + ':00.000Z';
    let end = new Date(endStr);
    let endDate = new Date(
      startDate.getFullYear(),
      startDate.getMonth(),
      startDate.getDate(),
      startDate.getHours(),
      startDate.getMinutes() + 30
    );
    while (endDate <= end) {
      let isPredefined: boolean = false;
      //let isReserved: boolean = false;
      for (let a of data.predefinedAppointments) {
        if (
          a.startTime[2] === data.selectedDayOfMonth &&
          a.startTime[3] === startDate.getHours() &&
          a.startTime[4] === startDate.getMinutes()
        ) {
          isPredefined = true;
          break;
        }
      }
      // for (let a of data.reservedAppointments) {
      //   if (
      //     a.startTime[2] === data.selectedDayOfMonth &&
      //     a.startTime[3] === startDate.getHours() &&
      //     a.startTime[4] === startDate.getMinutes()
      //   ) {
      //     isReserved = true;
      //     break;
      //   }
      // }

      //&& !isReserved
      if (!isPredefined) {
        if (startDate.getMinutes() === 0)
          this.availableAppointments.push(
            new String(
              startDate.getHours() +
                ':' +
                startDate.getMinutes() +
                '0' +
                ' - ' +
                endDate.getHours() +
                ':' +
                endDate.getMinutes()
            )
          );
        else if (endDate.getMinutes() === 0)
          this.availableAppointments.push(
            new String(
              startDate.getHours() +
                ':' +
                startDate.getMinutes() +
                ' - ' +
                endDate.getHours() +
                ':' +
                endDate.getMinutes() +
                '0'
            )
          );
        else {
          this.availableAppointments.push(
            new String(
              startDate.getHours() +
                ':' +
                startDate.getMinutes() +
                ' - ' +
                endDate.getHours() +
                ':' +
                endDate.getMinutes()
            )
          );
        }
      }

      startDate = endDate;
      endDate = new Date(
        startDate.getFullYear(),
        startDate.getMonth(),
        startDate.getDate(),
        startDate.getHours(),
        startDate.getMinutes() + 30
      );
    }
  }

  ok(selectedDate: String): void {
    this.dialogRef.close({ selectedDate });
  }

  onCancel(): void {
    this.dialogRef.close();
  }
}
