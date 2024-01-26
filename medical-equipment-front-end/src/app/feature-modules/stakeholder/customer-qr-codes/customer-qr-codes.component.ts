import { Component } from '@angular/core';
import { StakeholderService } from '../stakeholder.service';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';
import { ShowAppointment } from 'src/app/shared/model/appointment.model';
import { Item } from 'src/app/shared/model/item.model';

@Component({
  selector: 'app-customer-qr-codes',
  templateUrl: './customer-qr-codes.component.html',
  styleUrls: ['./customer-qr-codes.component.css'],
})
export class CustomerQrCodesComponent {
  appointments: ShowAppointment[] = [];
  appointmentsDisplay: ShowAppointment[] = [];
  selectedOption: string = '';
  appointmentItemsMap: { [id: number]: Item[] } = {};

  constructor(
    public router: Router,
    private service: StakeholderService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.getCustomerReservations();
  }

  getCustomerReservations(): void {
    this.service
      .getScheduledAppointmentsByCustomerId(
        this.authService.getCurrentUserId().toString()
      )
      .subscribe({
        next: (result) => {
          this.appointments = result;
          this.appointmentsDisplay = result;
          this.appointments.forEach((a) => {
            this.getItems(a.id);
          });
        },
        error: () => {
          console.log(console.error);
        },
      });
  }

  getItems(appointmentId: number): void {
    this.service.getItemsByAppointmentId(String(appointmentId)).subscribe({
      next: (result) => {
        this.appointmentItemsMap[appointmentId] = result;
      },
      error: () => {
        console.log(console.error);
      },
    });
  }

  selectChip(type: string): void {
    this.selectedOption = type;
    this.refreshAppointments();
    if (type == 'New') {
      this.appointmentsDisplay = this.appointments.filter(
        (a) =>
          this.appointmentItemsMap[a.id][0].pickedUp === false &&
          this.appointmentItemsMap[a.id][0].qrCodeProcessed === false
      );
    }
    if (type == 'Processed') {
      this.appointmentsDisplay = this.appointments.filter(
        (a) =>
          this.appointmentItemsMap[a.id][0].pickedUp === true &&
          this.appointmentItemsMap[a.id][0].qrCodeProcessed === true
      );
    }
    if (type == 'Rejected') {
      this.appointmentsDisplay = this.appointments.filter(
        (a) =>
          this.appointmentItemsMap[a.id][0].pickedUp === false &&
          this.appointmentItemsMap[a.id][0].qrCodeProcessed === true
      );
    }
  }

  refresh(): void {
    this.selectedOption = '';
    this.refreshAppointments();
  }

  refreshAppointments(): void {
    this.appointmentsDisplay = [];
    this.appointments.forEach((a) => this.appointmentsDisplay.push(a));
  }
}
