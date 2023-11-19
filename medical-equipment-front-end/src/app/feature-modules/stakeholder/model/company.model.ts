import { Address } from "./address.model";
export interface Company {
    id?:number;
    name: string;
    address: Address;
    startTime: string;
    endTime: string;
    description: string;
    averageRating? : number;
}