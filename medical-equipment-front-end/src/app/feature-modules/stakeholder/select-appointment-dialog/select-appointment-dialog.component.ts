import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { CompanyProfileComponent } from '../company-profile/company-profile.component';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-select-appointment-dialog',
  templateUrl: './select-appointment-dialog.component.html',
  styleUrls: ['./select-appointment-dialog.component.css'],
})
export class SelectAppointmentDialogComponent {
  availableDates: Date[] = [];
  constructor(
    public dialogRef: MatDialogRef<CompanyProfileComponent>,
    private router: Router,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    const currentDate = new Date();
    for (let i = 0; i < 4; i++) {
      this.availableDates.push(
        new Date(
          currentDate.getFullYear(),
          currentDate.getMonth(),
          currentDate.getDate() + i
        )
      );
    }
  }

  ok(selectedDate: Date): void {
    console.log(selectedDate);
    this.dialogRef.close();
  }

  onCancel(): void {
    this.dialogRef.close();
  }
}
