import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Equipment, EquipmentType } from 'src/app/shared/model/equipment.model';
import { EquipmentViewComponent } from '../equipment-view/equipment-view.component';
import { StakeholderService } from '../stakeholder.service';
import { MatDialogRef } from '@angular/material/dialog';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';
import { User } from 'src/app/infrastructure/auth/model/user.model';

@Component({
  selector: 'app-equipment-registration',
  templateUrl: './equipment-registration.component.html',
  styleUrls: ['./equipment-registration.component.css']
})

export class EquipmentRegistrationComponent implements OnInit {
  newEquipmentForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required]),
    type: new FormControl(null, [Validators.required]),
    price: new FormControl(0, [Validators.required,  positiveNumberValidator()]),
  });
  selectedType: string = '';
  user: any;

  constructor(
    private service: StakeholderService,
    private dialogRef: MatDialogRef<EquipmentRegistrationComponent>,
    private authService: AuthService
  ) {}
  
  ngOnInit(): void {
    this.authService.user$.subscribe((user) => {
      this.user = user;
    });
  }
  
  registerEquipment(): void {  
    let equipment: any;
    if(this.newEquipmentForm.valid){
      equipment = {
        name: this.newEquipmentForm.value.name || '',
        type: this.newEquipmentForm.value.type || null,
        description: this.newEquipmentForm.value.description || '',
        price: this.newEquipmentForm.value.price || 0,
      };
    }
  
    if (this.newEquipmentForm.valid) {
      this.service.registerNewEquipment(equipment).subscribe(
        (response) => {
          console.log('Registration successful:', response);
        },
        (error) => {
          console.error('Registration failed:', error);
        }
      );
  
      this.dialogRef.close();
    }
  }
}

function positiveNumberValidator(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    const valid = /^[1-9]\d*$/.test(control.value);

    return valid ? null : { invalidPositiveNumber: { value: control.value } };
  };
}

