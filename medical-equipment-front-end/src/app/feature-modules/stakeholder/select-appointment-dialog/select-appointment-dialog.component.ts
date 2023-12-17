import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { CompanyProfileComponent } from '../company-profile/company-profile.component';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Appointment } from 'src/app/shared/model/appointment.model';

@Component({
  selector: 'app-select-appointment-dialog',
  templateUrl: './select-appointment-dialog.component.html',
  styleUrls: ['./select-appointment-dialog.component.css'],
})
export class SelectAppointmentDialogComponent {
  availableAppointments: String[] = [];
  appointments: Appointment[] = [];
  constructor(
    public dialogRef: MatDialogRef<CompanyProfileComponent>,
    private router: Router,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    for (let i = 0; i < data.appointments.length; i++) {
      if (data.appointments[i].startTime[2] === data.selectedDayOfMonth)
        if (data.appointments[i].endTime[4] == '0') {
          this.appointments.push(data.appointments[i]);
          this.availableAppointments.push(
            new String(
              data.appointments[i].startTime[3] +
                ':' +
                data.appointments[i].startTime[4] +
                ' - ' +
                data.appointments[i].endTime[3] +
                ':' +
                data.appointments[i].endTime[4] +
                '0'
            )
          );
        } else {
          this.appointments.push(data.appointments[i]);
          this.availableAppointments.push(
            new String(
              data.appointments[i].startTime[3] +
                ':' +
                data.appointments[i].startTime[4] +
                ' - ' +
                data.appointments[i].endTime[3] +
                ':' +
                data.appointments[i].endTime[4]
            )
          );
        }
    }
  }

  ok(selectedDate: String): void {
    const selectedAppointment = this.appointments.find(
      (appointment) =>
        appointment.startTime[3].toString() ==
          selectedDate.split(' ')[0].split(':')[0] &&
        appointment.startTime[4].toString() ==
          selectedDate.split(' ')[0].split(':')[1]
    );
    this.dialogRef.close({ selectedAppointment });
  }

  onCancel(): void {
    this.dialogRef.close();
  }
}
