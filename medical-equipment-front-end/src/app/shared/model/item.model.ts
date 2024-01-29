import { Appointment } from './appointment.model';
import { Company } from './company.model';
import { CustomerProfile } from './customer-profile.model';
import { Equipment } from './equipment.model';

export interface CreateItem {
  id?: number;
  count: number;
  appointment?: Appointment;
  equipment?: Equipment;
  customerId: number;
  companyId: number;
  pickedUp: boolean;
  qrCodeProcessed: boolean;
}

export interface Item {
  id?: number;
  count: number;
  appointment?: Appointment;
  equipment?: Equipment;
  customer: CustomerProfile;
  company: Company;
  pickedUp: boolean;
  qrCodeProcessed: boolean;
}
