import { CompanyAdministrator } from "./company-administrator.model";
import { CustomerProfile } from "./customer-profile.model";

export interface Appointment {
    id?: number;
    start: string;
    end: string;
    companyAdministrator: CompanyAdministrator;
    customer: CustomerProfile;
  }