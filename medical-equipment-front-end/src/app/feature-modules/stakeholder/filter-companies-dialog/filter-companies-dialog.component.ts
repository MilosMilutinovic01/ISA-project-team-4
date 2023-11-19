import { Component, Inject } from '@angular/core';
import { CompaniesViewComponent } from '../companies-view/companies-view.component';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-filter-companies-dialog',
  templateUrl: './filter-companies-dialog.component.html',
  styleUrls: ['./filter-companies-dialog.component.css']
})
export class FilterCompaniesDialogComponent {

  filterForm = new FormGroup({
    averageRate : new FormControl('')
  })

  constructor(public dialogRef: MatDialogRef<CompaniesViewComponent>,
    private router:Router,
    @Inject(MAT_DIALOG_DATA) public data: any){

  }

  ok() : void{
    this.data.averageRate = this.filterForm.value.averageRate;
    console.log(this.data.averageRate);
    this.dialogRef.close(this.data.averageRate);
  }

  onCancel() : void{
    this.dialogRef.close();
  }
}
