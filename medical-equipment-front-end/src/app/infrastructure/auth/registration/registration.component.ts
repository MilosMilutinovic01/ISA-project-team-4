import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import {
  FormControl,
  FormGroup,
  Validators,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { Registration } from '../model/registration.model';
import { faXmark, faEye, faEyeSlash } from '@fortawesome/free-solid-svg-icons';
import { Router } from '@angular/router';
import { Address } from 'src/app/shared/model/address.model';
import { StakeholderService } from 'src/app/feature-modules/stakeholder/stakeholder.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
})
export class RegistrationComponent {
  isPassword1Visible: boolean;
  isPassword2Visible: boolean;
  faXmark = faXmark;
  faEye = faEye;
  faEyeSlash = faEyeSlash;

  constructor(
    private authService: AuthService,
    private router: Router,
    private stakeholderService: StakeholderService
  ) {
    this.isPassword1Visible = false;
    this.isPassword2Visible = false;
  }

  registrationForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    lastname: new FormControl('', [Validators.required]),
    username: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
    rePassword: new FormControl('', [Validators.required]),
    street: new FormControl('', [Validators.required]),
    city: new FormControl('', [Validators.required]),
    country: new FormControl('', [Validators.required]),
    phoneNumber: new FormControl('', [
      Validators.required,
      Validators.pattern(/^\d{10}$/),
    ]),
    profession: new FormControl('', [Validators.required]),
  });

  register(): void {
    const address: Address = {
      street: this.registrationForm.value.street || '',
      city: this.registrationForm.value.city || '',
      country: this.registrationForm.value.country || '',
      lat: NaN,
      lng: NaN,
    };

    const registration: Registration = {
      name: this.registrationForm.value.name || '',
      lastname: this.registrationForm.value.lastname || '',
      username: this.registrationForm.value.username || '',
      password: this.registrationForm.value.password || '',
      address: address,
      phoneNumber: this.registrationForm.value.phoneNumber || '',
      profession: this.registrationForm.value.profession || '',
    };

    if (
      this.registrationForm.valid &&
      this.registrationForm.controls['password'].value ===
        this.registrationForm.controls['rePassword'].value
    ) {
      this.authService.register(registration).subscribe({
        next: () => {
          alert('We sent you verification mail!');
          this.stakeholderService.setIsRegister(true);
          this.router.navigate(['']);
        },
        error: (error) => {
          if (error.status === 409) {
            alert('Email already exist!');
          }
        },
      });
    } else if (
      this.registrationForm.controls['password'].value !==
      this.registrationForm.controls['rePassword'].value
    ) {
      alert('Must enter same passwords!');
    } else {
      alert('Must enter valid fields!');
    }
  }

  togglePassword1Visibility() {
    this.isPassword1Visible = !this.isPassword1Visible;
  }

  togglePassword2Visibility() {
    this.isPassword2Visible = !this.isPassword2Visible;
  }
}
