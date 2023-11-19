import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StakeholderService } from '../stakeholder.service';
import { CompanyAdministrator } from '../../../shared/model/company-administrator.model';
import { Address } from '../../../shared/model/address.model';

@Component({
  selector: 'app-company-administrator-profile',
  templateUrl: './company-administrator-profile.component.html',
  styleUrls: ['./company-administrator-profile.component.css']
})
export class CompanyAdministratorProfileComponent implements OnInit {
    profile: CompanyAdministrator = {
        id: NaN,
        name: '',
        address: { id: NaN, street:'', city:'',country:''},
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
      this.service.getCompanyAdministratorProfile('1').subscribe({
        next: (result: CompanyAdministrator) => {
          this.profile = result;
          console.log("CompanyAdmin this.profile:");
          console.log(this.profile);
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
