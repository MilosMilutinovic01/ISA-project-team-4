import { Equipment } from './equipment.model';
export interface Contract {
  hospital: string;
  equipment: Equipment;
  count: number;
  dateInMonth: number;
  cancelledThisMonth: boolean;
}

export interface ShowContract {
  hospital: string;
  equipment: string;
  count: number;
  date: Date;
  canCancel: boolean;
}