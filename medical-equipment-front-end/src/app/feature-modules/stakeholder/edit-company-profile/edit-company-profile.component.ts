import { Component, OnInit } from '@angular/core';
import { StakeholderService } from '../stakeholder.service';
import { Router } from '@angular/router';
import { Company } from '../../../shared/model/company.model';
import { Address } from '../../../shared/model/address.model';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { min } from 'rxjs';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';

@Component({
  selector: 'app-edit-company-profile',
  templateUrl: './edit-company-profile.component.html',
  styleUrls: ['./edit-company-profile.component.css'],
})
export class EditCompanyProfileComponent implements OnInit {
  profile: Company = {
    id: NaN,
    name: '',
    address: { id: NaN, street: '', city: '', country: '' },
    startTime: '',
    endTime: '',
    description: '',
    averageRating: NaN,
  };

  editProfileForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    street: new FormControl('', [Validators.required]),
    city: new FormControl('', [Validators.required]),
    country: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required]),
    averageRating: new FormControl(0, [Validators.required]),
  });

  constructor(
    private router: Router,
    private snackBar: MatSnackBar,
    private service: StakeholderService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.getCompanyProfile();
  }

  getCompanyProfile(): void {
    this.service
      .getCompanyProfile(this.authService.getCurrentUserId().toString())
      .subscribe({
        next: (result: Company) => {
          this.profile = result;
          this.editProfileForm.patchValue({
            name: result.name,
            street: result.address.street,
            city: result.address.city,
            country: result.address.country,
            description: result.description,
            averageRating: result.averageRating,
          });
        },
        error: () => {
          console.log(console.error());
        },
      });
  }

  saveChanges(): void {
    const editAddress: Address = {
      id: this.profile.address.id,
      street: this.editProfileForm.value.street || '',
      city: this.editProfileForm.value.city || '',
      country: this.editProfileForm.value.country || '',
    };
    const editProfile: Company = {
      id: this.profile.id,
      name: this.editProfileForm.value.name || '',
      address: editAddress,
      startTime: this.profile.startTime,
      endTime: this.profile.endTime,
      description: this.editProfileForm.value.description || '',
      averageRating: this.editProfileForm.value.averageRating || 0,
    };

    if (this.editProfileForm.valid) {
      this.service.editCompanyProfile(editProfile).subscribe({
        next: (result: Company) => {
          this.profile = result;
          this.router.navigate(['/companyProfile']);
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
