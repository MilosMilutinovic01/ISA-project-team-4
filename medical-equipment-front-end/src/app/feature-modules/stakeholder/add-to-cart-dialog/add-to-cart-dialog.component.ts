import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { CompanyProfileComponent } from '../company-profile/company-profile.component';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-to-cart-dialog',
  templateUrl: './add-to-cart-dialog.component.html',
  styleUrls: ['./add-to-cart-dialog.component.css']
})
export class AddToCartDialogComponent {

  countForm = new FormGroup({
    count: new FormControl(1, [
      Validators.min(1),
      Validators.max(this.data.availableCount),
    ]),
  });

  constructor(public dialogRef: MatDialogRef<CompanyProfileComponent>,
    private router:Router,
    @Inject(MAT_DIALOG_DATA) public data: any){}

  ok() : void{
    this.data.count = this.countForm.value.count;
    console.log(this.data.count);
    if (this.countForm.valid) {
      this.dialogRef.close(this.data.count);
      alert('Added to cart!');
    }
    else alert('Must enter valid number!');
  }

  onCancel() : void{
    this.dialogRef.close();
  }
}
