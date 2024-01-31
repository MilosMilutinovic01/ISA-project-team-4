import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/infrastructure/auth/model/user.model';
import { ShowAppointment } from 'src/app/shared/model/appointment.model';
import { Item } from 'src/app/shared/model/item.model';
import { StakeholderService } from '../stakeholder.service';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';

@Component({
  selector: 'app-customer-pickup-history',
  templateUrl: './customer-pickup-history.component.html',
  styleUrls: ['./customer-pickup-history.component.css'],
})
export class CustomerPickupHistoryComponent {
  appointments: ShowAppointment[] = [];
  user: User | undefined;
  appointmentItemsMap: { [id: number]: Item[] } = {};
  totalPriceMap: { [id: number]: number } = {};

  dateAsc: string = 'dateAsc';
  dateDesc: string = 'dateDesc';
  selected: string = '';

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
      .getPickedUpAppointmentsByCustomerId(
        this.authService.getCurrentUserId().toString()
      )
      .subscribe({
        next: (result) => {
          this.appointments = result;

          // show only appointments that are in the future
          // this.appointments = this.appointments.filter(
          //   (a) =>
          //     new Date(
          //       Date.UTC(
          //         parseInt(a.startTime[0]), // Year
          //         parseInt(a.startTime[1]) - 1, // Month (zero-based)
          //         parseInt(a.startTime[2]), // Day
          //         parseInt(a.startTime[3]), // Hour
          //         parseInt(a.startTime[4]) // Minute
          //       )
          //     ) < new Date()
          // );

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

  onSelected(): void {
    if (this.selected === 'dateAsc') {
      this.sortDateAsc();
    }
    if (this.selected === 'dateDesc') {
      this.sortDateDesc();
    }
  }

  sortDateDesc(): void {
    this.appointments.sort((a, b) => {
      const startTimeA = new Date(
        parseInt(a.startTime[0]), // year
        parseInt(a.startTime[1]) - 1, // month (months are zero-based in JavaScript Date)
        parseInt(a.startTime[2]) // day
      );

      const startTimeB = new Date(
        parseInt(b.startTime[0]), // year
        parseInt(b.startTime[1]) - 1, // month (months are zero-based in JavaScript Date)
        parseInt(b.startTime[2]) // day
      );

      // Compare the start times
      return startTimeB.getTime() - startTimeA.getTime();
    });
  }

  sortDateAsc(): void {
    this.appointments.sort((a, b) => {
      const startTimeA = new Date(
        parseInt(a.startTime[0]), // year
        parseInt(a.startTime[1]) - 1, // month (months are zero-based in JavaScript Date)
        parseInt(a.startTime[2]), // day
        parseInt(a.startTime[3]), // hour
        parseInt(a.startTime[4]) // minute
      );

      const startTimeB = new Date(
        parseInt(b.startTime[0]), // year
        parseInt(b.startTime[1]) - 1, // month (months are zero-based in JavaScript Date)
        parseInt(b.startTime[2]), // day
        parseInt(b.startTime[3]), // hour
        parseInt(b.startTime[4]) // minute
      );

      // Compare the start times
      return startTimeA.getTime() - startTimeB.getTime();
    });
  }

  refresh(): void {
    this.getCustomerReservations();
    this.selected = '';
  }
}
