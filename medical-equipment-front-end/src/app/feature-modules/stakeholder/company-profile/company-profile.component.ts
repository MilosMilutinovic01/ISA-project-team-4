import { Component } from '@angular/core';
import { User } from 'src/app/infrastructure/auth/model/user.model';
import { StakeholderService } from '../stakeholder.service';
import { Company } from 'src/app/infrastructure/auth/model/company.model';

@Component({
  selector: 'app-company-profile',
  templateUrl: './company-profile.component.html',
  styleUrls: ['./company-profile.component.css']
})
export class CompanyProfileComponent {
  company : Company = {
    id : '0',
    name : '',
    address : '',
    city : '',
    country : '', 
    startTime : '',
    endTime : '',
    description : '',
    averageRating : '',
  }
  companyId : string = '';

  constructor(private service: StakeholderService) { }

  ngOnInit(): void {
    this.getCompany();
  }

  getCompany(): void {
    this.companyId = '1';
    this.service.getCompany(this.companyId).subscribe({
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
    
  }
}
