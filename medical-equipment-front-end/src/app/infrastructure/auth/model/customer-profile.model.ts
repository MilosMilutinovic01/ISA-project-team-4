export interface CustomerProfile {
    id?: number;
    name: string;
    lastname: string;
    email: string;
    address: string;
    city: string;
    country: string;
    phoneNumber: string;
    profession: string;
    penaltyPoints?: number;
    password: string;
    category?: string;
  }
  