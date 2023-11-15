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
}
