import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/infrastructure/auth/model/user.model';
import { StakeholderService } from '../../stakeholder/stakeholder.service';
import { CustomerProfile } from 'src/app/shared/model/customer-profile.model';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';
import { Address } from 'src/app/shared/model/address.model';
import { SystemAdministrator } from 'src/app/shared/model/system-administrator.model';

@Component({
  selector: 'xp-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  isDropdownOpen = false;
  systemAdminEnabled = false;
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
      if (this.user.role === 'SYSTEM_ADMINISTRATOR') {
        this.isFirstLogin();
      }
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
    this.router.navigate(['/administratorRegistration/ca']);
  }

  systemAdministratorRegistration(): void {
    this.router.navigate(['/administratorRegistration/sa']);
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

  isFirstLogin() {
    if (this.user) {
      this.service.getSystemAdministrator(this.user.id).subscribe({
        next: (result: SystemAdministrator) => {
          if (!result.hasLoggedBefore) {
            this.router.navigate(['/changePassword']);
            this.systemAdminEnabled = false;
          } else {
            this.systemAdminEnabled = true;
          }
        },
        error: (error) => {
          console.error('Error getting system administrator:', error);
        },
      });
    } else {
      console.warn('User is undefined. Cannot check first login status.');
    }
  }

  openScheduledAppointments(): void {
    this.router.navigate(['/scheduledAppointments']);
  }

  deliverEquipmentWithQR(): void {
    this.router.navigate(['/equipmentDeliverQR/']);
  }

  openPickUpHistory(): void {
    this.router.navigate(['/customerPickUp/']);
  }

  openQrCodes(): void {
    this.router.navigate(['/customerQrCodes/']);
  }
}
