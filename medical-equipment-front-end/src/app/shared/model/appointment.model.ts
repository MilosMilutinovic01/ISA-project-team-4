import { CompanyAdministrator } from './company-administrator.model';
import { CustomerProfile } from './customer-profile.model';

export interface Appointment {
  id?: number;
  startTime: string;
  endTime: string;
  companyAdministrator: CompanyAdministrator;
}

export interface ShowAppointment {
  id: number;
  startTime: string;
  endTime: string;
  companyAdministrator: CompanyAdministrator;
}
