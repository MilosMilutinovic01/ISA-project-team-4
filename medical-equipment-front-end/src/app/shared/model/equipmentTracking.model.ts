import { Company } from './company.model';
import { Equipment } from './equipment.model';

export interface EquipmentTracking {
  id?: number;
  count: number;
  company: Company;
  equipment: Equipment;
}
