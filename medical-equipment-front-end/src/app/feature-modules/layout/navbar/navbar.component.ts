import { Component, OnInit } from '@angular/core';
import { RegistrationComponent } from 'src/app/infrastructure/auth/registration/registration.component';
import { Router } from '@angular/router';
import { environment } from 'src/env/environment';
import { User } from 'src/app/infrastructure/auth/model/user.model';
import { StakeholderService } from '../../stakeholder/stakeholder.service';
import { CustomerProfile } from 'src/app/shared/model/customer-profile.model';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';
import { Address } from 'src/app/shared/model/address.model';

@Component({
  selector: 'xp-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  displayProfile: boolean = false;
  user: CustomerProfile = {
    name: '',
    lastname: '',
    email: '',
    address: {} as Address,
    phoneNumber: '',
    profession: '',
    password: '',
  };
  constructor(public router: Router, public service: StakeholderService) {
    this.service.getIsRegister.subscribe((msg) => (this.displayProfile = msg));
  }

  ngOnInit(): void {
    this.service.getCustomerProfile('1').subscribe({
      next: (result: CustomerProfile) => {
        this.user = result;
        this.displayProfile = true;
      },
      error: () => {
        this.displayProfile = false;
      },
    });
  }

  login(): void {
    this.router.navigate(['/login']);
  }

  register(): void {
    this.router.navigate(['/register']);
  }

  customerProfile(): void {
    this.router.navigate(['/customerProfile']);
  }

  companyRegistration(): void {
    this.router.navigate(['/companyRegistration']);
  }
  companyAdministratorRegistration(): void {
    this.router.navigate(['/companyAdministratorRegistration']);
  }
  companyProfile(): void {
    this.router.navigate(['/companyProfile']);
  }
  navigateToMedicalEquipment(): void {
    this.router.navigate(['/']);
  }
  
  companyAdministratorProfile(): void {
    this.router.navigate(['/companyAdministratorProfile']);
  }
}
