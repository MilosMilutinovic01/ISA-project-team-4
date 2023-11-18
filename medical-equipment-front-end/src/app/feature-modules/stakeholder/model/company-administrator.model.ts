import { Company } from "./company.model";
export interface CompanyAdministrator {
    id?:number;
    name: string;
    address: string;
    email: string;
    password?: string;
    lastname: string;
    city: string;
    country: string;
    phoneNumber: string;
    companyId?: number;
}