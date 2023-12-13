import { Component, OnInit } from '@angular/core';
import { faXmark, faEye, faEyeSlash } from '@fortawesome/free-solid-svg-icons';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CustomerProfile } from 'src/app/shared/model/customer-profile.model';
import { StakeholderService } from '../stakeholder.service';
import { Address } from 'src/app/shared/model/address.model';
import { User } from 'src/app/infrastructure/auth/model/user.model';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';

@Component({
  selector: 'app-edit-customer-profile',
  templateUrl: './edit-customer-profile.component.html',
  styleUrls: ['./edit-customer-profile.component.css'],
})
export class EditCustomerProfileComponent implements OnInit {
  isPasswordVisible: boolean = false;
  user: User | undefined;
  profile: CustomerProfile = {
    id: NaN,
    name: '',
    lastname: '',
    username: '',
    address: { id: NaN, street: '', city: '', country: '' },
    phoneNumber: '',
    profession: '',
    penaltyPoints: NaN,
    password: '',
    category: '',
  };

  editProfileForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    lastname: new FormControl('', [Validators.required]),
    street: new FormControl('', [Validators.required]),
    city: new FormControl('', [Validators.required]),
    country: new FormControl('', [Validators.required]),
    phoneNumber: new FormControl('', [
      Validators.required,
      Validators.pattern(/^\d{10}$/),
    ]),
    profession: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });

  constructor(
    private router: Router,
    private snackBar: MatSnackBar,
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
          this.editProfileForm.patchValue({
            name: result.name,
            lastname: result.name,
            street: result.address.street,
            city: result.address.city,
            country: result.address.country,
            phoneNumber: result.phoneNumber,
            profession: result.profession,
            password: result.password,
          });
        },
        error: () => {
          console.log(console.error());
        },
      });
  }

  saveChanges(): void {
    const userAddress: Address = {
      id: this.profile.address.id,
      street: this.editProfileForm.value.street || '',
      city: this.editProfileForm.value.city || '',
      country: this.editProfileForm.value.country || '',
    };
    const editProfile: CustomerProfile = {
      id: this.profile.id,
      name: this.editProfileForm.value.name || '',
      lastname: this.editProfileForm.value.lastname || '',
      username: this.profile.username,
      address: userAddress,
      phoneNumber: this.editProfileForm.value.phoneNumber || '',
      profession: this.editProfileForm.value.profession || '',
      penaltyPoints: this.profile.penaltyPoints,
      password: this.editProfileForm.value.password || '',
      category: this.profile.category,
    };

    if (this.editProfileForm.valid) {
      this.service.editCustomerProfile(editProfile).subscribe({
        next: (result: CustomerProfile) => {
          this.profile = result;

          this.router.navigate(['/customerProfile']);
          this.snackBar.open('Succesfully edited profile', 'Close', {
            duration: 5000,
          });
        },
        error: () => {
          console.log(console.error());
        },
      });
    } else {
      this.snackBar.open('All fields must be entered correctly!', 'Close', {
        duration: 5000,
      });
    }
  }
}
