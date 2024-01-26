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
  isDisabledMap: { [id: number]: boolean } = {};

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

  sortDateAsc(): void {
    this.service.sortAppointmentsByDate('1', this.appointments).subscribe({
      next: (result: ShowAppointment[]) => {
        this.appointments = result;
        console.log(result);
        this.appointments.forEach((a) => {
          this.getItems(a.id);
        });
      },
      error: () => {
        console.log(console.error());
      },
    });
  }

  sortDateDesc(): void {
    this.service.sortAppointmentsByDate('-1', this.appointments).subscribe({
      next: (result: ShowAppointment[]) => {
        this.appointments = result;
        console.log(result);
        this.appointments.forEach((a) => {
          this.getItems(a.id);
        });
      },
      error: () => {
        console.log(console.error());
      },
    });
  }

  refresh(): void {
    this.getCustomerReservations();
    this.selected = '';
  }
}
