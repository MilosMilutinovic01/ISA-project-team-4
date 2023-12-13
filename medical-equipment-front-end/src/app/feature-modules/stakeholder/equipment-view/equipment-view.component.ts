import { Component, OnInit } from '@angular/core';
import { StakeholderService } from '../stakeholder.service';
import { Router } from '@angular/router';
import { Equipment, EquipmentType } from 'src/app/shared/model/equipment.model';
import { EquipmentTracking } from 'src/app/shared/model/equipmentTracking.model';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatChipOption } from '@angular/material/chips';
import { MatSelectionListChange } from '@angular/material/list';

@Component({
  selector: 'app-equipment-view',
  templateUrl: './equipment-view.component.html',
  styleUrls: ['./equipment-view.component.css'],
})
export class EquipmentViewComponent implements OnInit {
  equipment: Equipment[] = [];
  equipmentTracking: EquipmentTracking[] = [];
  selectedOption: string = 'empty';

  searchForm = new FormGroup({
    name: new FormControl(''),
  });

  constructor(public router: Router, private service: StakeholderService) {}

  ngOnInit(): void {
    this.getEquipment();
  }

  getEquipment(): void {
    this.service.getEquipment().subscribe({
      next: (result: Equipment[]) => {
        this.equipment = result;
      },
      error: () => {
        console.log(console.error());
      },
    });
  }

  selectChip(type: string): void {
    this.selectedOption = type;
    const name = this.searchForm.value.name || 'empty';

    this.service.searchEquipment(name, this.selectedOption).subscribe({
      next: (result: EquipmentTracking[]) => {
        this.equipmentTracking = result;
        this.equipment = [];
      },
      error: () => {
        console.log(console.error());
      },
    });
  }

  search(): void {
    const name = this.searchForm.value.name || 'empty';

    this.service.searchEquipment(name, this.selectedOption).subscribe({
      next: (result: EquipmentTracking[]) => {
        this.equipmentTracking = result;
        this.equipment = [];
      },
      error: () => {
        console.log(console.error());
      },
    });
  }

  refresh(): void {
    this.getEquipment();
    this.equipmentTracking = [];
    this.searchForm.setValue({ name: '' });
    this.selectedOption = 'empty';
  }
}
