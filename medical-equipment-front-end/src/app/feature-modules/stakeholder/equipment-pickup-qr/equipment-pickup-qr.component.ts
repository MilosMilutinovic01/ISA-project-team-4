import { Component } from '@angular/core';
import { StakeholderService } from '../stakeholder.service';
import { Appointment } from 'src/app/shared/model/appointment.model';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-equipment-pickup-qr',
  templateUrl: './equipment-pickup-qr.component.html',
  styleUrls: ['./equipment-pickup-qr.component.css']
})

export class EquipmentPickupQrComponent {
  selectedFile: File | null = null;
  reservationInformation: String | undefined;
  appointmentId: any;
  appointment: any;
  message: String = '';
  canPickUp: boolean = true;
  user: any;

  constructor(private service: StakeholderService, private authService: AuthService,public router: Router) { }

  ngOnInit(): void {
    this.authService.user$.subscribe((user) => {
      this.user = user;
    });
  }

  onFileSelected(event: any): void {
    const fileInput = event.target;

    if (fileInput.files && fileInput.files.length > 0) {
      this.selectedFile = fileInput.files[0];
      console.log(this.selectedFile?.name);
      if (this.selectedFile?.name) {
        this.service.getAppointmentDataFromQRCode(this.selectedFile.name).subscribe(
          (result: String) => {
            console.log(result)
            this.appointmentId = result.split('\n')[0].split(' ')[3]
            console.log(this.appointmentId)
            this.reservationInformation = result.replace(/\n/g, "<br>");
            this.getAppointment()
          },
          (error) => {
            console.error(error);
          }
        );
      }
    } else {
      this.selectedFile = null;
    }
  }

getAppointment(): void {
  this.service.getAppointment(this.appointmentId).subscribe(
    (result: Appointment) => {
      const currentTime = new Date();
      this.appointment = result;
      const [year, month, day, hour, minute] = this.appointment.endTime;
      const endTime = new Date(year, month - 1, day, hour, minute);
      if(endTime < currentTime){
        this.message = '<br>Reservation has expired on ' + endTime.toDateString()
        this.canPickUp = false
        this.processExpiredReservation()
      }
      else if(this.appointment.companyAdministrator.id !== this.user.id){
        this.message = '<br>This reservation is assigned to another administrator'
        this.canPickUp = false
      }
      else{
        this.isReservationAvailable()
      }
    },
    (error) => {
      console.error(error);
    }
  );
}

isReservationAvailable(): void {
  this.service.isReservationAvailable(this.appointmentId).subscribe(
    (result: boolean) => {
      if(!result){
        this.message = '<br>Items have already been picked up for this reservation'
        this.canPickUp = false
      }
    },
    (error) => {
      console.error(error);
    }
  );
}

completeReservation(): void {
  this.service.completeReservation(this.appointment.id).subscribe({
    next: (result: boolean) => {
      if (result === true) {
        alert('Succesfully delivered equipment!');
        this.router.navigate(['/']);
      } else {
        alert('Failed to deliver equipment!');
      }
    },
    error: (error) => {
      console.log('Error:', error);
    },
  });
}

processExpiredReservation(): void {
  this.service.processExpiredReservation(this.appointment.id).subscribe({
    next: (result: boolean) => {
      if (result === true) {
        this.message += "<br>Customer received 2 penalty points, and the equipment count has been updated."
      } else {
        alert('Failed to process expired reservation!');
      }
    },
    error: (error) => {
      console.log('Error:', error);
    },
  });
}

}