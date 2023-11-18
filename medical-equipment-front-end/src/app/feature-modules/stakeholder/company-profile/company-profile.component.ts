import { Component } from '@angular/core';
import { User } from 'src/app/infrastructure/auth/model/user.model';
import { StakeholderService } from '../stakeholder.service';
import { Company } from 'src/app/infrastructure/auth/model/company.model';
import { MatDialog } from '@angular/material/dialog';
import { CompanyAdministartorRegistrationComponent } from '../company-administartor-registration/company-administartor-registration.component';

@Component({
  selector: 'app-company-profile',
  templateUrl: './company-profile.component.html',
  styleUrls: ['./company-profile.component.css']
})
export class CompanyProfileComponent {
  company : Company = {
    id : '28',
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

  constructor(private service: StakeholderService,private dialog: MatDialog) { }

  ngOnInit(): void {
    this.getCompany();
  }

  getCompany(): void {
    this.companyId = '28';
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

  addCompanyAdministrator(): void {
      const dialogRef = this.dialog.open(CompanyAdministartorRegistrationComponent, {
        width: '50%', 
        height: '100%',
      });

      dialogRef.afterClosed().subscribe(result => {
        console.log(result)
      });
      
  }
}
