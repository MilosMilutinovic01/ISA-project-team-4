import { Address } from 'src/app/shared/model/address.model';

export interface Registration {
  password: string;
  email: string;
  address: Address;
  name: string;
  lastname: string;
  phoneNumber: string;
  profession: string;
}
