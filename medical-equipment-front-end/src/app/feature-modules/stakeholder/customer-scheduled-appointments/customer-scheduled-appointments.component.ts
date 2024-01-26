import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { StakeholderService } from '../stakeholder.service';
import { User } from 'src/app/infrastructure/auth/model/user.model';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';
import {
  Appointment,
  ShowAppointment,
} from 'src/app/shared/model/appointment.model';
import { Item } from 'src/app/shared/model/item.model';

@Component({
  selector: 'app-customer-scheduled-appointments',
  templateUrl: './customer-scheduled-appointments.component.html',
  styleUrls: ['./customer-scheduled-appointments.component.css'],
})
export class CustomerScheduledAppointmentsComponent {
  appointments: ShowAppointment[] = [];
  appointmentItemsMap: { [id: number]: Item[] } = {};
  isDisabledMap: { [id: number]: boolean } = {};
  totalPriceMap: { [id: number]: number } = {};

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
          console.log('APP: ', result);

          // show only appointments that are in the future
          this.appointments = this.appointments.filter(
            (a) =>
              new Date(
                Date.UTC(
                  parseInt(a.startTime[0]), // Year
                  parseInt(a.startTime[1]) - 1, // Month (zero-based)
                  parseInt(a.startTime[2]), // Day
                  parseInt(a.startTime[3]), // Hour
                  parseInt(a.startTime[4]) // Minute
                )
              ) >= new Date()
          );

          this.appointments.forEach((a) => {
            this.getItems(a.id);
            this.canCustomerCancel(a);
          });
        },
        error: () => {
          console.log(console.error);
        },
      });
  }

  canCustomerCancel(a: ShowAppointment): void {
    let today = new Date();
    let startDate = new Date(
      Date.UTC(
        parseInt(a.startTime[0]), // Year
        parseInt(a.startTime[1]) - 1, // Month (zero-based)
        parseInt(a.startTime[2]), // Day
        parseInt(a.startTime[3]), // Hour
        parseInt(a.startTime[4]) // Minute
      )
    );

    const diffInMs = Math.floor(
      Date.UTC(
        startDate.getFullYear(),
        startDate.getMonth(),
        startDate.getDate(),
        startDate.getHours(),
        startDate.getMinutes()
      ) -
        Date.UTC(
          today.getFullYear(),
          today.getMonth(),
          today.getDate(),
          today.getHours(),
          today.getMinutes()
        )
    );
    const diffInHours = diffInMs / 1000 / 60 / 60;
    if (diffInHours <= 24) {
      this.isDisabledMap[a.id] = true;
    }
    if (diffInHours > 24) {
      this.isDisabledMap[a.id] = false;
    }
  }

  getItems(appointmentId: number): void {
    this.service.getItemsByAppointmentId(String(appointmentId)).subscribe({
      next: (result) => {
        this.appointmentItemsMap[appointmentId] = result;

        let totalPrice = 0;
        for (let i of result) {
          totalPrice += i.count * Number(i.equipment?.price);
        }

        this.totalPriceMap[appointmentId] = totalPrice;
      },
      error: () => {
        console.log(console.error);
      },
    });
  }

  cancelReservation(id: number): void {
    this.service.cancelReservation(id.toString()).subscribe({
      next: (result) => {
        if (result === true) {
          alert(
            'Succesfully cancelled reservation! You got one penalty point!'
          );
        } else alert('Error occured!');
      },
      error: () => {
        console.log(console.error);
      },
    });
  }
}
