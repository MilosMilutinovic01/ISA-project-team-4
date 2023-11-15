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

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
})
export class RegistrationComponent {
  isPasswordVisible: boolean;
  faXmark = faXmark;
  faEye = faEye;
  faEyeSlash = faEyeSlash;

  constructor(private authService: AuthService) {
    this.isPasswordVisible = false;
  }

  registrationForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    lastname: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    address: new FormControl('', [Validators.required]),
    city: new FormControl('', [Validators.required]),
    country: new FormControl('', [Validators.required]),
    phoneNumber: new FormControl('', [
      Validators.required,
      Validators.pattern(/^\d{10}$/),
    ]),
    profession: new FormControl('', [Validators.required]),
  });

  register(): void {
    const registration: Registration = {
      name: this.registrationForm.value.name || '',
      lastname: this.registrationForm.value.lastname || '',
      email: this.registrationForm.value.email || '',
      username: this.registrationForm.value.username || '',
      password: this.registrationForm.value.password || '',
      address: this.registrationForm.value.address || '',
      city: this.registrationForm.value.city || '',
      country: this.registrationForm.value.country || '',
      phoneNumber: this.registrationForm.value.phoneNumber || '',
      profession: this.registrationForm.value.profession || '',
    };

    if (this.registrationForm.valid) {
      this.authService.register(registration).subscribe({
        next: () => {
          alert('Succesfully created!');
          //add routing to home page or something like that
        },
      });
    } else {
      alert('Must enter valid fields!');
    }
  }

  togglePasswordVisibility() {
    this.isPasswordVisible = !this.isPasswordVisible;
  }
}
