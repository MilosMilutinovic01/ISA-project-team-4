import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { RegistrationComponent } from './registration/registration.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { HttpClientModule } from '@angular/common/http';
import { MaterialModule } from '../material/material.module';
import { VerificationComponent } from './verification/verification.component';

@NgModule({
  declarations: [RegistrationComponent, VerificationComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FontAwesomeModule,
    HttpClientModule,
    MaterialModule,
  ],
  exports: [],
})
export class AuthModule {}
