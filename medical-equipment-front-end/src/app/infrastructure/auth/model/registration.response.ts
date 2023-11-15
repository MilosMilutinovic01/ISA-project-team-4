import { User } from './user.model';

export interface RegistrationResponse {
  id: number;
  username: string;
  password: string;
  email: string;
  address: string;
  name: string;
  lastname: string;
  city: string;
  country: string;
  phoneNumber: string;
  profession: string;
  user: User;
}
