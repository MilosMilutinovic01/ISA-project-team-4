import { Component } from '@angular/core';
import { RegistrationComponent } from 'src/app/infrastructure/auth/registration/registration.component';
import { Router } from '@angular/router';
import { environment } from 'src/env/environment';

@Component({
  selector: 'xp-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent {
  constructor(public router: Router) {}

  register(): void {
    this.router.navigate(['/register']);
  }

  customerProfile(): void {
    this.router.navigate(['/customerProfile']);
  }

  companyRegistration(): void {
    this.router.navigate(['/companyRegistration']);
  }


  companiesView(): void {
    this.router.navigate(['/companiesView']);
  }

  companyAdministratorRegistration(): void {
    this.router.navigate(['/companyAdministratorRegistration']);
  }
  companyProfile(): void {
    this.router.navigate(['/companyProfile']);
  }
  equipment(): void {
    this.router.navigate(['/equipment']);
  }
  navigateToMedicalEquipment(): void {
    this.router.navigate(['/']);
  }
}
