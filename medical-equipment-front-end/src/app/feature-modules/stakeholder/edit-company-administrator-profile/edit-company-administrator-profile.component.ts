import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { StakeholderService } from '../stakeholder.service';
import { Address } from 'src/app/shared/model/address.model';
import { CompanyAdministrator } from 'src/app/shared/model/company-administrator.model';
import { UpdateCompanyAdministrator } from 'src/app/shared/model/update-company-administrator.model';

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
    this.service.getCompanyAdministratorProfile("1").subscribe({
      next:(result : CompanyAdministrator) => {
          this.profile = result;
          console.log("ID PROFILA ")
          console.log(this.profile.id)
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
    console.log(this.profile.id)
    console.log(this.editProfileForm.value.password )
    console.log(this.editProfileForm.value.email)
    console.log(editAddress)
    console.log(this.editProfileForm.value.name)
    console.log(this.editProfileForm.value.lastname)
    console.log(this.editProfileForm.value.phoneNumber)
    console.log(this.profile.companyId)
    const editProfile: UpdateCompanyAdministrator = {
      id: this.profile.id,
      password: this.editProfileForm.value.password || '',
      email: this.editProfileForm.value.email || '',
      address: editAddress,
      name: this.editProfileForm.value.name || '',
      lastname: this.editProfileForm.value.lastname || '',
      phoneNumber: this.editProfileForm.value.phoneNumber || '',
      companyId: this.profile.companyId
    };

    if(this.editProfileForm.valid){        
        this.service.editCompanyAdministratorProfile(editProfile).subscribe({
          next: (result : UpdateCompanyAdministrator) => {
            this.profile.id = result.id;
            this.profile.name = result.name;            
            this.profile.address = result.address;
            this.profile.email = result.email;
            this.profile.password = result.password;
            this.profile.lastname = result.lastname;
            this.profile.city = result.address.city;
            this.profile.country = result.address.country;
            this.profile.phoneNumber = result.phoneNumber;
            console.log('Profil nakon dodjele result: ')
            console.log(this.profile)
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
