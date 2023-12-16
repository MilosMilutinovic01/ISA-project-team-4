import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
  isDropdownOpen = false;
  user: User | undefined;
  customer: CustomerProfile = {
    name: '',
    lastname: '',
    username: '',
    address: {} as Address,
    phoneNumber: '',
    profession: '',
    password: '',
  };
  constructor(
    public router: Router,
    public service: StakeholderService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.authService.user$.subscribe((user) => {
      this.user = user;
    });
  }

  toggleDropdown() {
    this.isDropdownOpen = !this.isDropdownOpen;
  }

  login(): void {
    this.router.navigate(['/login']);
  }

  logout(): void {
    this.authService.logout();
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
    this.router.navigate(['/companyProfile/' + this.user?.id]);
  }
  navigateToMedicalEquipment(): void {
    this.router.navigate(['/']);
  }

  companyAdministratorProfile(): void {
    this.router.navigate(['/companyAdministratorProfile']);
  }
}
