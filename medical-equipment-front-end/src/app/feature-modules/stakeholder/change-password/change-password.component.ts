import { Component } from '@angular/core';
import { StakeholderService } from '../stakeholder.service';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';
import { MatDialogRef } from '@angular/material/dialog';
import { faXmark, faEye, faEyeSlash } from '@fortawesome/free-solid-svg-icons';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { SystemAdministrator } from 'src/app/shared/model/system-administrator.model';
import { Router } from '@angular/router';


@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent {
  isPassword1Visible: boolean = false;
  isPassword2Visible: boolean = false;
  faXmark = faXmark;
  faEye = faEye;
  faEyeSlash = faEyeSlash;
  systemAdministrator: any;
  user: any;
  constructor(private service: StakeholderService, private authService: AuthService, public router: Router) { }

  ngOnInit(): void {
    this.authService.user$.subscribe((user) => {
      this.user = user;
      this.getAdmin()
    });
  }

  passwordForm = new FormGroup({
    password: new FormControl('', [Validators.required]),
    rePassword: new FormControl('', [Validators.required])
  });

  changePassword(): void {
    const newPassword = this.passwordForm.value.password || '';
    if (this.passwordForm.valid && this.passwordForm.controls['password'].value === this.passwordForm.controls['rePassword'].value) {
      this.systemAdministrator.password = newPassword;
      this.systemAdministrator.hasLoggedBefore = true;
      this.service.updateSystemAdministrator(this.systemAdministrator).subscribe({
        next: () => {
          alert('Successfully changed password! Please log in again with your new password.');
          this.authService.logout()
        },
        error: (error) => {
          console.error('Error updating password:', error);
        },
      });
    } else if (
      this.passwordForm.controls['password'].value !==
      this.passwordForm.controls['rePassword'].value
    ) {
      alert('Must enter the same passwords!');
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

  getAdmin(){
    this.service
        .getSystemAdministrator(this.user.id)
        .subscribe({
          next: (result: SystemAdministrator) => {
            this.systemAdministrator = result
          },
          error: () => {
            console.log(console.error());
          },
        });
  }
}

