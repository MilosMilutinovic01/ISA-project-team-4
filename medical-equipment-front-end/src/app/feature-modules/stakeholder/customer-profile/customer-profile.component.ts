import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerProfile } from 'src/app/shared/model/customer-profile.model';
import { StakeholderService } from '../stakeholder.service';

@Component({
  selector: 'app-customer-profile',
  templateUrl: './customer-profile.component.html',
  styleUrls: ['./customer-profile.component.css'],
})
export class CustomerProfileComponent implements OnInit {
  hasPenaltyPoints: boolean = false;
  profile: CustomerProfile = {
    id: NaN,
    name: '',
    lastname: '',
    email: '',
    address: { street: '', city: '', country: '' },
    phoneNumber: '',
    profession: '',
    penaltyPoints: NaN,
    password: '',
    category: '',
  };
  constructor(public router: Router, private service: StakeholderService) {}

  ngOnInit(): void {
    this.getCustomerProfile();
  }

  getCustomerProfile(): void {
    this.service.getCustomerProfile('1').subscribe({
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
