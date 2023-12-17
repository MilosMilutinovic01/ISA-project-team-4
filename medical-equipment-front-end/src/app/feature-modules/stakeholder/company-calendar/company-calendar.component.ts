import { Component } from '@angular/core';
import { StakeholderService } from '../stakeholder.service';
import { Appointment } from 'src/app/shared/model/appointment.model';

@Component({
  selector: 'app-company-calendar',
  templateUrl: './company-calendar.component.html',
  styleUrls: ['./company-calendar.component.css']
})
export class CompanyCalendarComponent {

  appointments: Appointment[] = [];
  appointmentsData: any[] = [];
  constructor(
    private service: StakeholderService
  ) { }

  ngOnInit(): void {
    this.getAllAppointments();
  }

  getAllAppointments(): void {
    this.service.getAllAppointments().subscribe({
      next: (result: Appointment[]) => {
        this.appointments = result;
        result.forEach(appointment => {
          console.log(...appointment.startTime.toISOString());
        });
        this.mapiraj()
      },
      error: () => {
        console.log(console.error());
      },
    });
  }

  
  mapiraj() {
    this.appointmentsData = this.appointments.map(appointment => ({
      id: appointment.id,
      text: 'useer',
      startDate: new Date('2023-12-29T17:00:00.000Z'), // Convert to Date object
      endDate: new Date('2023-12-29T17:00:00.000Z') ,      // Convert to Date object
      color: '#99B080'
    }));
  
  }
  
  // appointmentsData: any[] = [
  //   {
  //     aid: 0,
  //     text: 'Petar Petrovic',
  //     startDate: new Date('2023-12-29T16:30:00.000Z'),
  //     endDate: new Date('2023-12-29T17:00:00.000Z'),
  //     available: false
  //   }, {
  //     aid: 1,
  //     text: 'Marko Markovic',
  //     startDate: new Date('2023-12-29T18:30:00.000Z'),
  //     endDate: new Date('2023-12-29T19:00:00.000Z'),
  //     available: false
  //   }, {
  //     aid: 2,
  //     text: 'Marija Markovic',
  //     startDate: new Date('2023-12-29T15:30:00.000Z'),
  //     endDate: new Date('2023-12-29T16:30:00.000Z'),
  //     available: false
  //   }, {
  //     aid: 3,
  //     text: 'SLOBODAN TERMIN',
  //     startDate: new Date('2023-12-15T17:00:00.000Z'),
  //     endDate: new Date('2023-12-15T17:30:00.000Z'),
  //     available: true
  //   }, {
  //     aid: 4,
  //     text: 'SLOBODAN TERMIN',
  //     startDate: new Date('2023-12-26T17:00:00.000Z'),
  //     endDate: new Date('2023-12-26T17:30:00.000Z'),
  //     available: true
  //   }, {
  //     aid: 5,
  //     text: 'SLOBODAN TERMIN',
  //     startDate: new Date('2023-12-16T12:30:00.000Z'),
  //     endDate: new Date('2023-12-16T13:00:00.000Z'),
  //     available: true
  //   }
  // ];

  // coloredAppointments: any[] = this.appointmentsData.map(appointment => ({
  //   id: appointment.aid,
  //   color: appointment.available ? '#99B080' : '#F9B572'
  // }));
}