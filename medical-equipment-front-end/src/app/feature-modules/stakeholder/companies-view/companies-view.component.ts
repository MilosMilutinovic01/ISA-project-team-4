import { Component, OnInit } from '@angular/core';
import { StakeholderService } from '../stakeholder.service';
import { Router } from '@angular/router';
import { Company } from 'src/app/shared/model/company.model';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { FilterCompaniesDialogComponent } from '../filter-companies-dialog/filter-companies-dialog.component';

@Component({
  selector: 'app-companies-view',
  templateUrl: './companies-view.component.html',
  styleUrls: ['./companies-view.component.css']
})
export class CompaniesViewComponent implements OnInit {
  companies : Company[] = [];
  rate : string = '';

  searchForm = new FormGroup({
    name: new FormControl(''),
    street: new FormControl(''),
    city: new FormControl(''),
    country: new FormControl('')
  })

  constructor(public router: Router,
    private service: StakeholderService,
    private dialog: MatDialog) {}

  ngOnInit(): void {
    this.getCompanies();
  }

  getCompanies():void{
    this.service.getCompanies().subscribe({
      next:(result : Company[]) => {
          console.log(result);
          this.companies = result;
      },
      error: () =>{
        console.log(console.error())
      }
    })
  }

  search():void{
    const name = this.searchForm.value.name || "empty";
    const street = this.searchForm.value.street || "empty";
    const city = this.searchForm.value.city || "empty";
    const country = this.searchForm.value.country || "empty";


    this.service.searchCompanies(name, street, city, country).subscribe({
      next:(result : Company[]) => {
        this.companies = result
        console.log(result)
      },
      error: () =>{
        console.log(console.error())
      }
    })
  }

  openFilterDialog(): void{
    const dialogRef = this.dialog.open(FilterCompaniesDialogComponent,{
      width: '30%',
      height: '65%',
      data: { rate: this.rate } 
      }).afterClosed().subscribe(result => {
        console.log(result);
        this.rate = result;
      });
  }

  refresh():void {
    this.getCompanies();
  }
}
