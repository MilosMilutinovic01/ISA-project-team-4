import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Contract, ShowContract } from 'src/app/shared/model/contract.model';
import { StakeholderService } from '../stakeholder.service';

@Component({
  selector: 'app-contract-list',
  templateUrl: './contract-list.component.html',
  styleUrls: ['./contract-list.component.css']
})

export class ContractListComponent {

  constructor(
    public router: Router,
    private service: StakeholderService
  ) { }

  displayedColumns: string[] = ['hospital', 'equipment', 'date', 'buttons'];
  contracts: Contract[] = [];
  showContracts: ShowContract[] = [];
  currentDate: any;

  ngOnInit(): void {
    this.getContracts();
  }

  getContracts(): void {
    this.service.getAllContracts().subscribe({
      next: (result) => {
        this.contracts = result;
        this.getShowContracts()
      },
      error: () => {
        console.log(console.error);
      },
    });
  }

  getShowContracts(): void {
    this.showContracts = [];
    this.currentDate = new Date();
    this.contracts.forEach((contract) => {
      let showContractDate = new Date(this.currentDate.getFullYear(),  this.currentDate.getMonth(),contract.dateInMonth,12,0,0);
      const newDate = new Date(this.currentDate.getFullYear(),  this.currentDate.getMonth() + 1,contract.dateInMonth,12,0,0);
      if (contract.dateInMonth < this.currentDate.getDate()) {
        showContractDate = newDate;
      }
      const isWithin24Hours = showContractDate.getTime() <= this.currentDate.getTime() + 24 * 60 * 60 * 1000;
      this.updateCancellation(contract.hospital, showContractDate)
      const showContract: ShowContract = {
        hospital: contract.hospital,
        equipment: contract.equipment.name,
        count: contract.count,
        date: showContractDate,
        canCancel: !contract.canceledThisMonth && !isWithin24Hours
      };
      this.showContracts.push(showContract);
    });
  }

  cancel(hospital: String): void {
    this.service.updateCancellation(hospital,true).subscribe({
      next: (result) => {
        const contractIndex = this.showContracts.findIndex(contract => contract.hospital === hospital);
        if (contractIndex !== -1) {
          const contract = this.showContracts[contractIndex];
          contract.canCancel = false;
        }
      },
      error: () => {
        console.log(console.error);
      },
    });
  }

  updateCancellation(hospital: String, showContractDate: Date): void {
    const today = new Date()
    if(showContractDate < today){
      this.service.updateCancellation(hospital,false).subscribe({
        next: (result) => {
        },
        error: () => {
          console.log(console.error);
        },
      });
    }
  }
}
