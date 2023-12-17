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
  nonAvailable: Appointment[] = [];
  appointments: Appointment[] = [];
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
        this.getAllAppointments()
      },
      error: () => {
        console.log(console.error());
      },
    });
  }

  getAllAppointments(): void {
    this.service.getAppointmentsByCompanyId(this.companyAdmin.companyId).subscribe({
      next: (result: Appointment[]) => {
        this.appointments = result;
        result.forEach(appointment => {
          var convertedDate = new Date(
            parseFloat(appointment.startTime[0]),
            parseFloat(appointment.startTime[1]) - 1,
            parseFloat(appointment.startTime[2]),
            parseFloat(appointment.startTime[3]),
            parseFloat(appointment.startTime[4])
          );
          console.log(convertedDate.toISOString())
          this.getCustomer(appointment)
        });
        //this.showAppointments()
      },
      error: () => {
        console.log(console.error());
      },
    });
  }

  getCustomer(appointment: any){
    this.service.getCustomerByAppointmentId(appointment.id).subscribe({
      next: (result: any) => {
        if(result !== null){
          //this.nonAvailable.push(appointment)
        }
        this.showAppointments()
      },
      error: () => {
        console.log(console.error());
      },
    });
  }

  showAppointments() {
    console.log(this.nonAvailable)
    this.appointmentsData = this.appointments.map(appointment => ({
      id: appointment.id,
      text: 'TERMIN ZA PREUZIMANJE',
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
      color: '#ffd3b6'
    }));

  }
}
