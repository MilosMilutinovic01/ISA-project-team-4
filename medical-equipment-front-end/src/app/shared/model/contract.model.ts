export interface Contract {
  hospital: string;
  equipment: string;
  count: number;
  dateInMonth: number;
}

export interface ShowContract {
  hospital: string;
  equipment: string;
  count: number;
  date: Date;
  canCancel: boolean;
}