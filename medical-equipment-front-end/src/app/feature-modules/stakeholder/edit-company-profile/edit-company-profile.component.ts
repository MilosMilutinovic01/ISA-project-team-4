import { Component, OnInit } from '@angular/core';
import { StakeholderService } from '../stakeholder.service';
import { Router } from '@angular/router';
import { Company } from 'src/app/infrastructure/auth/model/company.model';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-edit-company-profile',
  templateUrl: './edit-company-profile.component.html',
  styleUrls: ['./edit-company-profile.component.css']
})
export class EditCompanyProfileComponent implements OnInit{
  profile : Company = {
    id: NaN,
    name: '',
    address: '',
    city : '',
    country : '', 
    startTime : '',
    endTime : '',
    description: '',
    averageRating: NaN
  }

  editProfileForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    address: new FormControl('', [Validators.required]),
    city: new FormControl('', [Validators.required]),
    country: new FormControl('', [Validators.required]),
    startTime: new FormControl('', [Validators.required]),
    endTime: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required]),
    averageRating: new FormControl(0, [Validators.required])
  });


  constructor(private router: Router,
    private snackBar: MatSnackBar,
    private service: StakeholderService){
  }

  ngOnInit(): void {
    this.getCompanyProfile();
  }

  getCompanyProfile(): void{
    this.service.getCompanyProfile("1").subscribe({
      next:(result : Company) => {
          this.profile = result;
          console.log('GET COMPANY PROFILE '+result);
          this.editProfileForm.patchValue(result);
      },
      error: () =>{
        console.log(console.error());
      }
    }) 
  }

  saveChanges(): void{
    const editProfile: Company = {
      id: this.profile.id,
      name: this.editProfileForm.value.name || '',
      address: this.editProfileForm.value.address || '',
      city: this.profile.city,
      country: this.profile.country,
      startTime: this.profile.startTime,
      endTime: this.profile.endTime,
      description: this.editProfileForm.value.description || '',
      averageRating: this.editProfileForm.value.averageRating || 0
    };

    if(this.editProfileForm.valid){
        const editProfile: Company = {
          id: this.profile.id,
          name: this.editProfileForm.value.name || '',
          address: this.editProfileForm.value.address || '',
          city: this.profile.city,
          country: this.profile.country,
          startTime: this.profile.startTime,
          endTime: this.profile.endTime,
          description: this.editProfileForm.value.description || '',
          averageRating: this.editProfileForm.value.averageRating || 0
        };
      
        console.log('SAVE CHANGES editprofile: ')
        console.log(editProfile);
        
        this.service.editCompanyProfile(editProfile).subscribe({
          next: (result : Company) => {
            this.profile = result;
            
            console.log('AFTER SAVE CHANGES ');
            console.log(this.profile)
            
            this.router.navigate(['/companyProfile']);
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

