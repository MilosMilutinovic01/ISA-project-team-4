import { Company } from "./company.model";
import { Address } from "./address.model";

export interface CompanyAdministrator {
    id?:number;
    name: string;
    address: Address;
    email: string;
    password?: string;
    lastname: string;
    city: string,
    country: string,
    phoneNumber: string;
    companyId?: number;
}