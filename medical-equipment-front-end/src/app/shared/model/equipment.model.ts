export interface Equipment {
    id?: number;
    name: string;
    description: string;
    type: EquipmentType;
    price: number;
  }

  export enum EquipmentType {
    LABORATORY,
    DENTAL,
    SURGICAL,
    DIAGNOSTIC
    }