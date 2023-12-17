import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { StakeholderService } from '../stakeholder.service';
import { Company } from '../../../shared/model/company.model';
import { Address } from '../../../shared/model/address.model';
import { MatDialog } from '@angular/material/dialog';
import { EquipmentTracking } from 'src/app/shared/model/equipmentTracking.model';
import { CompanyAdministrator } from 'src/app/shared/model/company-administrator.model';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';
import { FormGroup, FormControl } from '@angular/forms';
import { User } from 'src/app/infrastructure/auth/model/user.model';
import { AddToCartDialogComponent } from '../add-to-cart-dialog/add-to-cart-dialog.component';
import { Item } from 'src/app/shared/model/item.model';
import { Appointment } from 'src/app/shared/model/appointment.model';

@Component({
  selector: 'app-company-profile',
  templateUrl: './company-profile.component.html',
  styleUrls: ['./company-profile.component.css'],
})
export class CompanyProfileComponent {
  company: Company = {
    id: NaN,
    name: '',
    address: { id: NaN, street: '', city: '', country: '' },
    description: '',
    startTime: '',
    endTime: '',
    averageRating: NaN,
  };

  // appointment: Appointment = {
  //   ccid: NaN,
  //   startTime: '',
  //   endTime: '',
  //   companyAdministrator: { id:NaN,
  //     name: '',
  //     address: { id: NaN, street: '', city: '', country: '' },
  //     email: '',
  //     password: '',
  //     lastname: '',
  //     city: '',
  //     country: '',
  //     phoneNumber: '',
  //     companyId: NaN },
  //   customer: { id:NaN,
  //     name: '',
  //     lastname: '',
  //     username: '',
  //     address: { id: NaN, street: '', city: '', country: '' },
  //     phoneNumber: '',
  //     profession: '',
  //     penaltyPoints: NaN,
  //     password: '',
  //     category: ''},
    
  // };

  user: User | undefined;
  otherAdministrators: CompanyAdministrator[] = [];
  filteredEquipmentTrackings: EquipmentTracking[] = [];
  equipmentTracking: EquipmentTracking[] = [];
  selectedOption: string = 'empty';

  equipmentCount: number = 0;
  items: Item[] = [];

  searchForm = new FormGroup({
    name: new FormControl(''),
  });


  constructor(
    private service: StakeholderService,
    private dialog: MatDialog,
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.authService.user$.subscribe((user) => {
      this.user = user;
    });
    this.getCompany();
    this.getAllEquipmentTrackings();
    this.getAllCompanyAdministrators();
  }

  getCompany(): void {
    this.service.getCompanyAdministratorProfile((this.user?.id!).toString()).subscribe({
      next: (result) => {
        this.service.getCompanyProfile(result.companyId.toString()).subscribe({
          next: (result) => {
            this.company = result;
            console.log(this.company);
          },
          error: () => {
            console.log(console.error);
          },
        });
      },
      error: () => {
        console.log(console.error);
      },
    });

    
    
  }

  editProfile(): void {
    this.router.navigate(['/editCompanyProfile']);
  }

  getAllEquipmentTrackings(): void {
    this.service.getAllEquipmentTrackings().subscribe({
      next: (result) => {
        this.equipmentTracking = result;
        this.sort();
      },
      error: () => {
        console.log(console.error);
      },
    });
  }

  selectChip(type: string): void {
    this.selectedOption = type;
    const name = this.searchForm.value.name || 'empty';

    this.service.searchEquipment(name, this.selectedOption).subscribe({
      next: (result: EquipmentTracking[]) => {
        this.equipmentTracking = result;
        this.sort();
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
        this.sort();
      },
      error: () => {
        console.log(console.error());
      },
    });
  }

  sort(): void {
    for (let el of this.equipmentTracking) {
      if (el.company.id === this.company.id && el.count > 0) {
        this.filteredEquipmentTrackings.push(el);
      }
    }
  }

  refresh(): void {
    this.equipmentTracking = [];
    this.searchForm.setValue({ name: '' });
    this.selectedOption = 'empty';
  }

  getAllCompanyAdministrators(): void {
    this.service.getAllCompanyAdministrators().subscribe({
      next: (result) => {
        for (let a of result) {
          if (a.companyId === this.company.id && a.id !== -1) {
            this.otherAdministrators.push(a);
          }
        }
      },
      error: () => {
        console.log(console.error);
      },
    });
  }

  addToCart(id: number): void {
    const dialogRef = this.dialog
      .open(AddToCartDialogComponent, {
        width: '45%',
        height: '35%',
        data: { count: this.equipmentCount },
      })
      .afterClosed()
      .subscribe((result) => {
        if(result){
          const item : Item = {
            count: result || '',
            customerId: this.authService.getCurrentUserId() || 0
          };
  
          const selectedEquipment = this.filteredEquipmentTrackings.find(e => e.id === id)?.equipment;
          item.equipment = selectedEquipment;
          console.log("ITEM: ", item);
  
          this.service.createItem(item).subscribe({
            next: (result) => {
              console.log("RES: ",result);
              this.items.push(result);
            },
            error: () => {
              console.log(console.error);
            },
          });
        }
      });

    }
    
    showCart():void{
      
    }
}
