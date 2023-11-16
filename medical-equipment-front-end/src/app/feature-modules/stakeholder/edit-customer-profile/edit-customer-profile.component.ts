import { Component } from '@angular/core';
import { faXmark, faEye, faEyeSlash } from '@fortawesome/free-solid-svg-icons';
import {
  FormControl,
  FormGroup,
  Validators
} from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-edit-customer-profile',
  templateUrl: './edit-customer-profile.component.html',
  styleUrls: ['./edit-customer-profile.component.css']
})
export class EditCustomerProfileComponent {
  isPasswordVisible: boolean = false;

  constructor(private router: Router,
    private snackBar: MatSnackBar){
  }

  editProfileForm = new FormGroup({
    name: new FormControl(''),
  });

  saveChanges(): void{
    this.router.navigate(['/customerProfile']);
    this.snackBar.open('Succesfully edited profile', 'Close', {
      duration: 5000
    });
  }
}
