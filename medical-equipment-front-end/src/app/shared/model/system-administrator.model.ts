import { Address } from './address.model';
export interface SystemAdministrator {
    id?:number;
    name: string;
    address: Address;
    username: string;
    password?: string;
    lastname: string;
    city: string,
    country: string,
    phoneNumber: string;
    hasLoggedBefore: boolean;
}