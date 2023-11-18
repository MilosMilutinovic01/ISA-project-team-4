import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { StakeholderService } from '../stakeholder.service';
import { Company } from 'src/app/infrastructure/auth/model/company.model';

@Component({
  selector: 'app-company-profile',
  templateUrl: './company-profile.component.html',
  styleUrls: ['./company-profile.component.css']
})
export class CompanyProfileComponent {
  company : Company = {
    id : NaN,
    name : '',
    address : '',
    description : '',
    city: '',
    country: '',
    startTime: '',
    endTime: '',
    averageRating : NaN,
  }
  companyId : string = '';

  constructor(private service: StakeholderService, private router: Router) { }

  ngOnInit(): void {
    this.getCompany();
  }

  getCompany(): void {
    this.companyId = '1';
    this.service.getCompanyProfile(this.companyId).subscribe({
      next: (result) =>{
        this.company = result;
        console.log(this.company);
      },     
      error: () => {
        console.log(console.error);
      }
    })
  }

  editProfile(): void {
    this.router.navigate(['/editCompanyProfile']);
  }
}
