import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StakeholderService } from '../stakeholder.service';
import { CompanyAdministrator } from '../model/company-administrator.model';

@Component({
  selector: 'app-company-administrator-profile',
  templateUrl: './company-administrator-profile.component.html',
  styleUrls: ['./company-administrator-profile.component.css']
})
export class CompanyAdministratorProfileComponent implements OnInit {
    profile: CompanyAdministrator = {
        id: NaN,
        name: '',
        address: '',
        email: '',
        password: '',
        lastname: '',
        city: '',
        country: '',
        phoneNumber: '',
        companyId: NaN
    };

    constructor(public router: Router, private service: StakeholderService) {}
  
    ngOnInit(): void {
      this.getCompanyAdministratorProfile();
    }
  
    getCompanyAdministratorProfile(): void {
      this.service.getCompanyAdministratorProfile('28').subscribe({
        next: (result: CompanyAdministrator) => {
          this.profile = result;
          console.log("CompanyAdmin this.profile:"+ result);
        },
        error: () => {
          console.log(console.error());
        },
      });
    }
  
    editProfile(): void {
      this.router.navigate(['/editCompanyAdministratorProfile']);
    }  
}
