import { Component, OnInit } from '@angular/core';
import { StakeholderService } from '../stakeholder.service';
import { Router } from '@angular/router';
import { Company } from 'src/app/shared/model/company.model';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-companies-view',
  templateUrl: './companies-view.component.html',
  styleUrls: ['./companies-view.component.css']
})
export class CompaniesViewComponent implements OnInit {
  companies : Company[] = [];

  searchForm = new FormGroup({
    name: new FormControl(''),
    address: new FormControl(''),
    city: new FormControl(''),
    country: new FormControl('')
  })

  constructor(public router: Router,
    private service: StakeholderService) {}

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

  }

}
