import { Address } from 'src/app/shared/model/address.model';

export interface Registration {
  password: string;
  username: string;
  address: Address;
  name: string;
  lastname: string;
  phoneNumber: string;
  profession: string;
}
