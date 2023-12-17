import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { CompanyProfileComponent } from '../company-profile/company-profile.component';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-add-to-cart-dialog',
  templateUrl: './add-to-cart-dialog.component.html',
  styleUrls: ['./add-to-cart-dialog.component.css']
})
export class AddToCartDialogComponent {

  countForm = new FormGroup({
    count : new FormControl('')
  })

  constructor(public dialogRef: MatDialogRef<CompanyProfileComponent>,
    private router:Router,
    @Inject(MAT_DIALOG_DATA) public data: any){}

  ok() : void{
    this.data.count = this.countForm.value.count;
    console.log(this.data.count);
    this.dialogRef.close(this.data.count);
  }

  onCancel() : void{
    this.dialogRef.close();
  }
}
