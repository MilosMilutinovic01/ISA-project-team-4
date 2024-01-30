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
      const showContractDate = new Date(this.currentDate.getFullYear(),  this.currentDate.getMonth(),contract.dateInMonth,12,0,0);
      const isWithin24Hours = showContractDate.getTime() <= this.currentDate.getTime() + 24 * 60 * 60 * 1000;
      const showContract: ShowContract = {
        hospital: contract.hospital,
        equipment: contract.equipment,
        count: contract.count,
        date: showContractDate,
        canCancel: isWithin24Hours,
      };
      this.showContracts.push(showContract);
    });
  }

  cancel(hospital: string): void {
    console.log(hospital)
  }
}
