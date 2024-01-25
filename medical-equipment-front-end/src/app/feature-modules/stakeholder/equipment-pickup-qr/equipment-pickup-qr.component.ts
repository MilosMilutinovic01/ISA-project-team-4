import { Component } from '@angular/core';
import { StakeholderService } from '../stakeholder.service';
import { Appointment } from 'src/app/shared/model/appointment.model';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';


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

  constructor(private service: StakeholderService, private authService: AuthService) { }

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
        this.message = 'Reservation has expired on ' + endTime.toDateString()
        this.canPickUp = false
      }
      else if(this.appointment.companyAdministrator.id !== this.user.id){
        this.message = 'This reservation is assigned to another administrator'
        this.canPickUp = false
      }
      else{
        this.isReservationComplete()
      }
    },
    (error) => {
      console.error(error);
    }
  );
}

isReservationComplete(): void {
  this.service.isReservationComplete(this.appointmentId).subscribe(
    (result: boolean) => {
      console.log(result)
    },
    (error) => {
      console.error(error);
    }
  );
}

}
