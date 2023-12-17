import { Appointment } from "./appointment.model";
import { Equipment } from "./equipment.model";

export interface Item {
    id?: number;
    count: number;
    appointment?: Appointment;
    equipment?: Equipment;
    customerId: number;
  }
  