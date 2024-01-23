import { Component } from '@angular/core';
import { StakeholderService } from '../stakeholder.service';
import { Appointment } from 'src/app/shared/model/appointment.model';
import { parseNumber } from 'devextreme/localization';
import { CompanyAdministrator } from 'src/app/shared/model/company-administrator.model';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';
import { CustomerProfile } from 'src/app/shared/model/customer-profile.model';

@Component({
  selector: 'app-company-calendar',
  templateUrl: './company-calendar.component.html',
  styleUrls: ['./company-calendar.component.css']
})
export class CompanyCalendarComponent {

  companyAdmin: any
  appointments: any[] = [];
  myDictionary: { [key: string]: any } = {};
  appointmentsData: any[] = [];
  constructor(
    private service: StakeholderService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.getCompanyAdmin();
  }

  getCompanyAdmin() {
    this.service.getCompanyAdministratorProfile(this.authService.getCurrentUserId().toString()).subscribe({
      next: (result: CompanyAdministrator) => {
        this.companyAdmin = result;
        this.getScheduledAppointments()
      },
      error: () => {
        console.log(console.error());
      },
    });
  }

  getScheduledAppointments(): void {
    this.service.getScheduledAppointmentsByCompanyId(this.companyAdmin.companyId).subscribe({
      next: (result: Appointment[]) => {
        this.appointments = result;
        result.forEach(appointment => {
          this.getCustomer(appointment)
        });
        this.getFreeAppointments()
      },
      error: () => {
        console.log(console.error());
      },
    });
  }

  getCustomer(appointment: any){
    this.service.getCustomerByAppointmentId(appointment.id).subscribe({
      next: (result: CustomerProfile) => {
        this.myDictionary[appointment.id] = result.name + ' ' + result.lastname
      },
      error: () => {
        console.log(console.error());
      },
    });
  }

  getFreeAppointments(): void {
    this.service.getFreeAppointmentsByCompanyId(this.companyAdmin.companyId).subscribe({
      next: (result: any[]) => {
        result.forEach(appointment => {
          this.appointments.push(appointment)
          this.myDictionary[appointment.id] = 'Slobodan termin'
        });
        this.showScheduledAppointments()
      },
      error: () => {
        console.log(console.error());
      },
    });
  }

  showScheduledAppointments() {
    this.appointmentsData = this.appointments.map(appointment => ({
      id: appointment.id,
      text: this.myDictionary[appointment.id],
      startDate: new Date(
        parseFloat(appointment.startTime[0]),
        parseFloat(appointment.startTime[1]) - 1,
        parseFloat(appointment.startTime[2]),
        parseFloat(appointment.startTime[3]) + 1,
        parseFloat(appointment.startTime[4])
      ).toISOString(),
      endDate: new Date(
        parseFloat(appointment.endTime[0]),
        parseFloat(appointment.endTime[1]) - 1,
        parseFloat(appointment.endTime[2]),
        parseFloat(appointment.endTime[3]) + 1,
        parseFloat(appointment.endTime[4])
      ).toISOString(),
      color: this.myDictionary[appointment.id] === 'Slobodan termin' ? '#4caf50' : '#FF6961'
    }));

  }
}
