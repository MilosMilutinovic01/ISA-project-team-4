import { Component } from '@angular/core';
import { StakeholderService } from '../stakeholder.service';


@Component({
  selector: 'app-equipment-pickup-qr',
  templateUrl: './equipment-pickup-qr.component.html',
  styleUrls: ['./equipment-pickup-qr.component.css']
})

export class EquipmentPickupQrComponent {
  selectedFile: File | null = null;
  reservationInformation: String|undefined;

  constructor(private service: StakeholderService) {}

  onFileSelected(event: any): void {
    const fileInput = event.target;
    
    if (fileInput.files && fileInput.files.length > 0) {
      this.selectedFile = fileInput.files[0];
      console.log(this.selectedFile?.name);
      if (this.selectedFile?.name) {
        this.service.getAppointmentDataFromQRCode(this.selectedFile.name).subscribe(
          (result: String) => {
            this.reservationInformation = result.replace(/\n/g, "<br>");
            console.log(this.reservationInformation);
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

}
