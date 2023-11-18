import { AddressModel } from "./address.model";
export interface CompanyModel {
    name: string;
    address: AddressModel;
    city: string;
    country: string;
    startTime: string;
    endTime: string;
    description: string;
}