import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customer-profile',
  templateUrl: './customer-profile.component.html',
  styleUrls: ['./customer-profile.component.css']
})
export class CustomerProfileComponent {
  constructor(public router: Router){}
  editProfile(): void{
    this.router.navigate(['/editCustomerProfile']);
  }
}
