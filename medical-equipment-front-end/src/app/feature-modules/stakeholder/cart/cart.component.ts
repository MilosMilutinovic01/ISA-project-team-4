import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Validators, FormBuilder } from '@angular/forms';
import { StakeholderService } from '../stakeholder.service';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';
import { ActivatedRoute } from '@angular/router';
import { Item } from 'src/app/shared/model/item.model';
import { SelectAppointmentDialogComponent } from '../select-appointment-dialog/select-appointment-dialog.component';
import { Appointment } from 'src/app/shared/model/appointment.model';
import { MatStepper } from '@angular/material/stepper';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent {
  @ViewChild('stepper') stepper: any;

  items : Item[] = [];
  totalPrice : number = 0;
  isIrregular: boolean = false;
  appointment: Appointment = {
    id: NaN,
    startTime: '',
    endTime: '',
    companyAdministrator: { id:NaN,
      name: '',
      address: { id: NaN, street: '', city: '', country: '' },
      username: '',
      password: '',
      lastname: '',
      city: '',
      country: '',
      phoneNumber: '',
      companyId: NaN }
  };
  selectedDate = new Date(Date.now());
  firstFormGroup = this._formBuilder.group({
    firstCtrl: ['', Validators.required],
  });
  secondFormGroup = this._formBuilder.group({
    secondCtrl: ['', Validators.required],
  });

  constructor(private _formBuilder: FormBuilder,
    private service: StakeholderService,
    private authService: AuthService,
    private route: ActivatedRoute,
    private dialog: MatDialog) {}

  ngOnInit():void{
    this.route.params.subscribe((params) => {
      
      this.service.getItemsByCustomerId(this.authService.getCurrentUserId().toString()).subscribe({
        next: (result) => {
          this.items = result.filter(
            i => i.company.id === Number(params["id"])
          );

          for (let i of this.items){
            this.totalPrice += i.count * Number(i.equipment?.price);
          }
        },
        error: () => {
          console.log(console.error);
        }
      });
    });
  }

  calculatePrice(item: Item):number{
    return item.count * Number(item.equipment?.price);
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(SelectAppointmentDialogComponent, {
      data: { selectedDate: this.selectedDate },
    });

    dialogRef.afterClosed().subscribe((result) => {
      console.log('Dialog closed with result:', result);
    });
  }

  chooseIrregular() : void{
    this.isIrregular = true;
  }

  choosePredefined() : void{
    this.isIrregular = false;
  }
}
