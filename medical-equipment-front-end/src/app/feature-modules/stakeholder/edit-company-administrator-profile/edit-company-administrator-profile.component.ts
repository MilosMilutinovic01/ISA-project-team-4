import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { StakeholderService } from '../stakeholder.service';
import { Address } from 'src/app/shared/model/address.model';
import { CompanyAdministrator } from 'src/app/shared/model/company-administrator.model';

@Component({
  selector: 'app-edit-company-administrator-profile',
  templateUrl: './edit-company-administrator-profile.component.html',
  styleUrls: ['./edit-company-administrator-profile.component.css']
})
export class EditCompanyAdministratorProfileComponent {
  profile : CompanyAdministrator = {
    id: NaN,
    name: '',
    address: { id: NaN, street:'', city:'',country:''},
    email : '',
    password : '',
    lastname: '',
    city: '',
    country: '',
    phoneNumber: '',
    companyId: NaN
  }

  editProfileForm = new FormGroup({
    name: new FormControl('', [Validators.required]), 
    street: new FormControl('', [Validators.required]),
    city: new FormControl('', [Validators.required]),
    country: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    lastname: new FormControl('', [Validators.required]),
    phoneNumber: new FormControl('', [Validators.required])
  });


  constructor(private router: Router,
    private snackBar: MatSnackBar,
    private service: StakeholderService){
  }

  ngOnInit(): void {
    this.getCompanyAdministratorProfile();
  }

  getCompanyAdministratorProfile(): void{
    this.service.getCompanyAdministratorProfile("-1").subscribe({
      next:(result : CompanyAdministrator) => {
          this.profile = result;
          this.editProfileForm.patchValue({
            name: result.name,
            street: result.address.street,
            city: result.address.city,
            country: result.address.country,
            email: result.email,
            password: result.password,
            lastname: result.lastname,
            phoneNumber: result.phoneNumber
          });
      },
      error: () =>{
        console.log(console.error());
      }
    }) 
  }

  saveChanges(): void{
    const editAddress: Address = {
      id: this.profile.address.id,
      street: this.editProfileForm.value.street || '',
      city: this.editProfileForm.value.city || '',
      country: this.editProfileForm.value.country || '',
    };
    const editProfile: CompanyAdministrator = {
      id: this.profile.id,
      name: this.editProfileForm.value.name || '',
      address: editAddress,
      email: this.editProfileForm.value.email || '',
      password: this.editProfileForm.value.password || '',
      lastname: this.editProfileForm.value.lastname || '',
      city: this.editProfileForm.value.city || '',
      country: this.editProfileForm.value.country || '',
      phoneNumber: this.editProfileForm.value.phoneNumber || ''
    };

    if(this.editProfileForm.valid){        
        this.service.editCompanyAdministratorProfile(editProfile).subscribe({
          next: (result : CompanyAdministrator) => {
            this.profile = result;            
            this.router.navigate(['/companyAdministratorProfile']);
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
