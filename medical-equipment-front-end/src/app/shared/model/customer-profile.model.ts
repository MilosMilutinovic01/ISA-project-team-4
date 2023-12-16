import { Address } from './address.model';

export interface CustomerProfile {
  id?: number;
  name: string;
  lastname: string;
  username: string;
  address: Address;
  phoneNumber: string;
  profession: string;
  penaltyPoints?: number;
  password: string;
  category?: string;
}
