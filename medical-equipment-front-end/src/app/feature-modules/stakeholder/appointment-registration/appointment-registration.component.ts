import { Component, OnInit } from '@angular/core';
import { Appointment } from 'src/app/shared/model/appointment.model';
import { StakeholderService } from '../stakeholder.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CompanyAdministrator } from 'src/app/shared/model/company-administrator.model';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';
import { User } from 'src/app/infrastructure/auth/model/user.model';
import { DatePipe } from '@angular/common';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';

@Component({
  selector: 'app-appointment-registration',
  templateUrl: './appointment-registration.component.html',
  styleUrls: ['./appointment-registration.component.css']
})
export class AppointmentRegistrationComponent {
  user: User | undefined;
  
  companyAdministrator: CompanyAdministrator = {
    id: NaN,
    name: '',
    address: { id: NaN, street:'', city:'',country:''},
    email: '',
    password: '',
    lastname: '',
    city: '',
    country: '',
    phoneNumber: '',
    companyId: NaN
  };

  administrators: CompanyAdministrator[] = [
    {
      name: 'Jovan',
      address: { street: '123 Main St', city: 'City', country: 'Country' },
      email: 'jovan@example.com',
      lastname: 'Lukic',
      city: 'City',
      country: 'Country',
      phoneNumber: '123-456-7890',
      companyId: - 1,
    },
    {
      name: 'Milica',
      address: { street: '123 Main St', city: 'City', country: 'Country' },
      email: 'john.doe@example.com',
      lastname: 'Savic',
      city: 'City',
      country: 'Country',
      phoneNumber: '123-456-7890',
      companyId: -1,
    },
  ];

  selectedAdmin: CompanyAdministrator = {} as CompanyAdministrator;
  minDate = new Date();
  
  appointmentForm = new FormGroup({
    administrator: new FormControl('', [Validators.required]),
    startDate: new FormControl('', [Validators.required]),
    startTime: new FormControl('', [Validators.required]),
    duration: new FormControl('30', [Validators.required]), 
  });

  constructor(
    private service: StakeholderService,
    private authService: AuthService,
    private dialogRef: MatDialogRef<AppointmentRegistrationComponent>
  ) {}
  
  dateFilter = (date: Date | null): boolean => {
    const today = new Date();
    today.setHours(0, 0, 0, 0); // Set hours to 0 for proper comparison
    return date! && date >= today;
  };

  registerAppointment(): void {
    console.log(' APPOINTMENT : Start time')
    console.log(this.appointmentForm.value.startTime)    
    console.log(this.appointmentForm.value.administrator)    
    console.log(this.appointmentForm.value.startDate)
    console.log(this.appointmentForm.value.duration)

    const startDateInput = new Date(this.appointmentForm.value.startDate!);
    console.log('startdate before ',startDateInput)
    startDateInput.setHours(parseInt(this.appointmentForm.value.startTime!.slice(0,2)));
    startDateInput.setMinutes(parseInt(this.appointmentForm.value.startTime!.slice(3,5)));
    console.log('startdate after ',startDateInput)
    const endDate = new Date(startDateInput.getTime() + 30 * 60000);

    console.log('Start Date:', startDateInput);
    console.log('End Date:', endDate); //Thu Dec 28 2023 00:00:00 GMT+0100 (Central European Standard Time)

    this.authService.user$.subscribe((user) => {
      this.user = user;
    });

    const appointment: Appointment = {
      startTime: startDateInput.toString(),
      endTime: endDate.toString(),
      companyAdministrator: this.getCompanyAdministratorProfile(),
    };

    console.log('Appointment ')
   // console.log(appointment)

    if (this.appointmentForm.valid) {
      this.service.registerAppointment(appointment).subscribe(
        (response) => {
          console.log('Registration successful:', response);
        },
        (error) => {
          console.error('Registration failed:', error);
        }
      );

      this.dialogRef.close(appointment);
    }
  }
    
  getCompanyAdministratorProfile(): CompanyAdministrator {
    let companyAdministrator: CompanyAdministrator = {
      id: NaN,
      name: '',
      address: { id: NaN, street:'', city:'',country:''},
      email: '',
      password: '',
      lastname: '',
      city: '',
      country: '',
      phoneNumber: '',
      companyId: NaN
    };

    this.service.getCompanyAdministratorProfile(this.authService.getCurrentUserId().toString()).subscribe({
      next: (result: CompanyAdministrator) => {
        this.companyAdministrator = result;
        console.log("CompanyAdmin this.profile:");
        console.log(this.companyAdministrator);
      },
      error: () => {
        console.log(console.error());
      },
    });
    return companyAdministrator;
  }
}
