import { Component, OnInit } from '@angular/core';
import { faXmark, faEye, faEyeSlash } from '@fortawesome/free-solid-svg-icons';
import {
  FormControl,
  FormGroup,
  Validators
} from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CustomerProfile } from 'src/app/infrastructure/auth/model/customer-profile.model';
import { StakeholderService } from '../stakeholder.service';

@Component({
  selector: 'app-edit-customer-profile',
  templateUrl: './edit-customer-profile.component.html',
  styleUrls: ['./edit-customer-profile.component.css']
})
export class EditCustomerProfileComponent implements OnInit{
  isPasswordVisible: boolean = false;
  profile : CustomerProfile = {
    id: NaN,
    name: '',
    lastname: '',
    email: '',
    address: '',
    city: '',
    country: '',
    phoneNumber: '',
    profession: '',
    penaltyPoints: NaN,
    password: '',
    category: ''
  }

  editProfileForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    lastname: new FormControl('', [Validators.required]),
    address: new FormControl('', [Validators.required]),
    city: new FormControl('', [Validators.required]),
    country: new FormControl('', [Validators.required]),
    phoneNumber: new FormControl('', [Validators.required, Validators.pattern(/^\d{10}$/),]),
    profession: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
  });


  constructor(private router: Router,
    private snackBar: MatSnackBar,
    private service: StakeholderService){
  }

  ngOnInit(): void {
    this.getCustomerProfile();
  }

  getCustomerProfile(): void{
    this.service.getCustomerProfile("12").subscribe({
      next:(result : CustomerProfile) => {
          this.profile = result;
          console.log(result);
          this.editProfileForm.patchValue(result);
      },
      error: () =>{
        console.log(console.error());
      }
    }) 
  }

  saveChanges(): void{
    const editProfile: CustomerProfile = {
      id: this.profile.id,
      name: this.editProfileForm.value.name || '',
      lastname: this.editProfileForm.value.lastname || '',
      email: this.profile.email,
      address: this.editProfileForm.value.address || '',
      city: this.editProfileForm.value.city || '',
      country: this.editProfileForm.value.country || '',
      phoneNumber: this.editProfileForm.value.phoneNumber || '',
      profession: this.editProfileForm.value.profession || '',
      penaltyPoints: this.profile.penaltyPoints,
      password: this.editProfileForm.value.password || '',
      category: this.profile.category
    };

    if(this.editProfileForm.valid){
        console.log(editProfile);
        this.service.editCustomerProfile(editProfile).subscribe({
          next: (result : CustomerProfile) => {
            this.profile = result;
            console.log(result);

            this.router.navigate(['/customerProfile']);
            this.snackBar.open('Succesfully edited profile', 'Close', {
              duration: 5000
            });
          },
          error: () => {
            console.log(console.error());
          }
        })
    }
    else{
      this.snackBar.open('All fields must be entered correctly!', 'Close', {
        duration: 5000
      });
    }
    
  }
}
