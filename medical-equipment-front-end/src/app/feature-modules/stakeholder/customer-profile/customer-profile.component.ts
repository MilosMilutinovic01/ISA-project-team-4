import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerProfile } from 'src/app/shared/model/customer-profile.model';
import { StakeholderService } from '../stakeholder.service';
import { User } from 'src/app/infrastructure/auth/model/user.model';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';

@Component({
  selector: 'app-customer-profile',
  templateUrl: './customer-profile.component.html',
  styleUrls: ['./customer-profile.component.css'],
})
export class CustomerProfileComponent implements OnInit {
  hasPenaltyPoints: boolean = false;
  user: User | undefined;
  profile: CustomerProfile = {
    id: NaN,
    name: '',
    lastname: '',
    username: '',
    address: {
      street: '',
      city: '',
      country: '',
      lat: NaN,
      lng: NaN,
    },
    phoneNumber: '',
    profession: '',
    penaltyPoints: NaN,
    password: '',
    category: '',
  };
  constructor(
    public router: Router,
    private service: StakeholderService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.getCustomerProfile();
    this.authService.user$.subscribe((user) => {
      this.user = user;
    });
  }

  getCustomerProfile(): void {
    this.service
      .getCustomerProfile(this.authService.getCurrentUserId().toString())
      .subscribe({
        next: (result: CustomerProfile) => {
          this.profile = result;
          console.log(result);

          if (!result.penaltyPoints) {
            this.hasPenaltyPoints = false;
          } else {
            this.hasPenaltyPoints = true;
          }
        },
        error: () => {
          console.log(console.error());
        },
      });
  }

  editProfile(): void {
    this.router.navigate(['/editCustomerProfile']);
  }
}
