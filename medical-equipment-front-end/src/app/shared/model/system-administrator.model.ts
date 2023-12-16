import { Address } from './address.model';
export interface SystemAdministrator {
    id?:number;
    name: string;
    address: Address;
    email: string;
    password?: string;
    lastname: string;
    city: string,
    country: string,
    phoneNumber: string;
    hasLoggedBefore: boolean;
}