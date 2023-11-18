import { CreateAddressModel } from "./create-address.model";
export interface CreateCompanyModel {
    name: string;
    address: CreateAddressModel;
    city: string;
    country: string;
    startTime: string;
    endTime: string;
    description: string;
}