import { Component, OnInit } from '@angular/core';
import { StakeholderService } from '../stakeholder.service';
import { Router } from '@angular/router';
import { Company } from 'src/app/infrastructure/auth/model/company.model';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { min } from 'rxjs';

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
          console.log(this.profile)
          console.log("Posle patch-ovanja u getCompanyProfile")
          console.log(this.editProfileForm)
      },
      error: () =>{
        console.log(console.error());
      }
    }) 
  }

  saveChanges(): void {
    let editProfile: Company;
  
    if (this.editProfileForm.valid) {
      editProfile = {
        id: this.profile.id,
        name: this.editProfileForm.value.name || '',
        address: this.editProfileForm.value.address || '',
        city: this.profile.city,
        country: this.profile.country,
        startTime: this.convertTimeToString(this.profile.startTime),
        endTime: this.convertTimeToString(this.profile.endTime),
        description: this.editProfileForm.value.description || '',
        averageRating: this.editProfileForm.value.averageRating || 0
      };
  
      console.log('SAVE CHANGES editprofile: ')
      console.log(editProfile);
  
      this.service.editCompanyProfile(editProfile).subscribe({
        next: (result: Company) => {
          this.profile = result;
  
          console.log('AFTER SAVE CHANGES ');
          console.log(this.profile);
  
          this.router.navigate(['/companyProfile']);
          this.snackBar.open('Successfully edited profile', 'Close', {
            duration: 5000
          });
        },
        error: (error) => {
          console.log('Error:', error);
          // Handle error, log more details if available
        }
      });
    } else {
      this.snackBar.open('All fields must be entered correctly!', 'Close', {
        duration: 5000
      });
    }
  }
  
  convertTimeToString(timeArray: string): string {
    // Convert time array [hours, minutes] to string format "HH:mm"
    console.log(timeArray) 
    const [hours, minutes] = timeArray;
    console.log('h: '+hours)
    console.log('m: '+minutes)
    const formattedTime = `${hours}:${minutes}`;
    return formattedTime;
  }
} 

