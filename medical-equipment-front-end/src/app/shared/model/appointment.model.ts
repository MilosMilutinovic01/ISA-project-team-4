import { CompanyAdministrator } from "./company-administrator.model";
import { CustomerProfile } from "./customer-profile.model";

export interface Appointment {
    id?: number;
    startTime: Date;
    endTime: Date;
    companyAdministrator: CompanyAdministrator;
  }