import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Validators, FormBuilder } from '@angular/forms';
import { SelectAppointmentDialogComponent } from '../select-appointment-dialog/select-appointment-dialog.component';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent {
  selectedDate = new Date(Date.now());
  firstFormGroup = this._formBuilder.group({
    firstCtrl: ['', Validators.required],
  });
  secondFormGroup = this._formBuilder.group({
    secondCtrl: ['', Validators.required],
  });

  constructor(private _formBuilder: FormBuilder, private dialog: MatDialog) {}

  ngOnInit(): void {}

  openDialog(): void {
    const dialogRef = this.dialog.open(SelectAppointmentDialogComponent, {
      data: { selectedDate: this.selectedDate },
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log('Dialog closed with result:', result);
    });
  }
}
