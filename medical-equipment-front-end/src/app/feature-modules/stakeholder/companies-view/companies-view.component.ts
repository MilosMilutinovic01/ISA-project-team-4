import { Component, OnInit } from '@angular/core';
import { StakeholderService } from '../stakeholder.service';
import { Router } from '@angular/router';
import { Company } from 'src/app/shared/model/company.model';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { FilterCompaniesDialogComponent } from '../filter-companies-dialog/filter-companies-dialog.component';

@Component({
  selector: 'app-companies-view',
  templateUrl: './companies-view.component.html',
  styleUrls: ['./companies-view.component.css'],
})
export class CompaniesViewComponent implements OnInit {
  companies: Company[] = [];
  rate: string = '';
  filterLabel: string = '';

  searchForm = new FormGroup({
    name: new FormControl(''),
    street: new FormControl(''),
    city: new FormControl(''),
    country: new FormControl(''),
  });

  constructor(
    public router: Router,
    private service: StakeholderService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.getCompanies();
  }

  getCompanies(): void {
    this.service.getCompanies().subscribe({
      next: (result: Company[]) => {
        this.companies = result;
      },
      error: () => {
        console.log(console.error());
      },
    });
  }

  showCompany(id: number): void {
    this.router.navigate(['/companyProfile/', id]);
  }

  search(): void {
    const name = this.searchForm.value.name || 'empty';
    const street = this.searchForm.value.street || 'empty';
    const city = this.searchForm.value.city || 'empty';
    const country = this.searchForm.value.country || 'empty';

    this.service.searchCompanies(name, street, city, country).subscribe({
      next: (result: Company[]) => {
        this.companies = result;
      },
      error: () => {
        console.log(console.error());
      },
    });
  }

  openFilterDialog(): void {
    const dialogRef = this.dialog
      .open(FilterCompaniesDialogComponent, {
        width: '30%',
        height: '65%',
        data: { rate: this.rate },
      })
      .afterClosed()
      .subscribe((result) => {
        this.rate = result;

        if (result) {
          this.filterCompanies(this.rate);
          this.changeFilterLabel(this.rate);
        }
      });
  }

  filterCompanies(rate: string): void {
    this.service.filterCompanies(rate, this.companies).subscribe({
      next: (result: Company[]) => {
        this.companies = result;
      },
      error: () => {
        console.log(console.error());
      },
    });
  }

  changeFilterLabel(rate: string): void {
    switch (parseInt(rate)) {
      case 1:
        this.filterLabel = 'Rate lesser than 1';
        break;
      case 2:
        this.filterLabel = 'Rate between 1 and 2';
        break;
      case 3:
        this.filterLabel = 'Rate between 2 and 3';
        break;
      case 4:
        this.filterLabel = 'Rate between 3 and 4';
        break;
      case 5:
        this.filterLabel = 'Rate between 4 and 5';
        break;
      default:
        this.filterLabel = '';
        break;
    }
  }

  refresh(): void {
    this.getCompanies();
    this.filterLabel = '';
    this.searchForm.setValue({ name: '', street: '', city: '', country: '' });
  }
}
