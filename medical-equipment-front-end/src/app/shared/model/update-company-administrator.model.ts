import { Company } from "./company.model";
import { Address } from "./address.model";

export interface UpdateCompanyAdministrator {
    id?:number;
    password?: string;
    email: string;
    address: Address;
    name: string;
    lastname: string;
    phoneNumber: string;
    companyId?: number;
}