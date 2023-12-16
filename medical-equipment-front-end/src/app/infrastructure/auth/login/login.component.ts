import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { faEye, faEyeSlash } from '@fortawesome/free-solid-svg-icons';
import { Login } from '../model/login.model';
import { Router } from '@angular/router';
import { User } from '@auth0/auth0-angular';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  isPasswordVisible: boolean;
  faEye = faEye;
  faEyeSlash = faEyeSlash;

  constructor(private authService: AuthService, private router: Router) {
    this.isPasswordVisible = false;
  }

  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
  });

  togglePasswordVisibility() {
    this.isPasswordVisible = !this.isPasswordVisible;
  }

  login(): void {
    const login: Login = {
      username: this.loginForm.value.email || '',
      password: this.loginForm.value.password || '',
    };
    if (this.loginForm.valid) {
      this.authService.login(login).subscribe({
        next: () => {
          alert('Succesfull log in!');
          this.router.navigate(['']);
        },
        error: (error) => {
          alert(error);
        },
      });
    }
  }
}
