import { Component, Inject, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, ValidatorFn, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { StakeholderService } from '../stakeholder.service';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';
import { EquipmentTracking } from 'src/app/shared/model/equipmentTracking.model';
import { Equipment, EquipmentType } from 'src/app/shared/model/equipment.model';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { User } from 'src/app/infrastructure/auth/model/user.model';

@Component({
  selector: 'app-edit-equipment-tracking',
  templateUrl: './edit-equipment-tracking.component.html',
  styleUrls: ['./edit-equipment-tracking.component.css']
})

export class EditEquipmentTrackingComponent implements OnInit {
  selectedEquipmentTracking: EquipmentTracking = {
    id: NaN,
    count: 0,
    company: {
      id: NaN,
      name: '',
      address: { id: NaN, street: '', city: '', country: '' },
      startTime: '',
      endTime: '',
      description: '',
      averageRating: NaN
    },
    equipment: {
      id: NaN,
      name: '',
      description: '',
      type: EquipmentType.DENTAL,
      price: 0
    }
  }
  user: User = {id: NaN, role: '', username: ''};
 
  editEquipmentTrackingForm = new FormGroup({
    count: new FormControl('', [Validators.required]),
    name: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required]),
    type: new FormControl('', [Validators.required]),
    price: new FormControl(0, [Validators.required]), 
  });

  constructor(
    private router: Router,
    private snackBar: MatSnackBar,
    private service: StakeholderService,
    private authService: AuthService, 
    @Inject(MAT_DIALOG_DATA) public data: any,
    private dialogRef: MatDialogRef<EditEquipmentTrackingComponent>){ }

  ngOnInit(): void {
    this.authService.user$.subscribe((user) => {
      this.user = user;
    });
    this.selectedEquipmentTracking = this.data.selectedEquipmentTracking;
    
    console.log('SELECTTED: ');
    console.log(this.selectedEquipmentTracking);
    
    this.editEquipmentTrackingForm.patchValue({
      count: this.selectedEquipmentTracking.count.toString(),
      name: this.selectedEquipmentTracking.equipment.name,
      description: this.selectedEquipmentTracking.equipment.description,
      type: this.selectedEquipmentTracking.equipment.type.toString(),
      price: this.selectedEquipmentTracking.equipment.price,
    });
  }

  saveChanges(): void{
    console.log(this.editEquipmentTrackingForm.value.count )
    
    let temp : EquipmentType
    if(this.editEquipmentTrackingForm.value.type === 'DENTAL'){
      temp = EquipmentType.DENTAL;
    }


    const editE: Equipment = {
      id: this.selectedEquipmentTracking.equipment.id,
      name: this.editEquipmentTrackingForm.value.name || '',
      type: temp! || EquipmentType.DENTAL,
      price: this.editEquipmentTrackingForm.value.price || NaN,
      description: this.editEquipmentTrackingForm.value.description || '',
    };

    console.log(editE)

    const editEquipmentTracking: EquipmentTracking = {
      id: this.selectedEquipmentTracking.id,
      count: parseInt(this.editEquipmentTrackingForm.value.count!) || NaN,
      company: this.selectedEquipmentTracking.company || undefined,
      equipment: editE
    };

    if(this.editEquipmentTrackingForm.valid){        
        this.service.editEquipmentTracking(editEquipmentTracking).subscribe({
          next: (result : EquipmentTracking) => {
            this.selectedEquipmentTracking.id = result.id;
            this.selectedEquipmentTracking.equipment.name = result.equipment.name;            
            this.selectedEquipmentTracking.equipment.description = result.equipment.description;
            this.selectedEquipmentTracking.count = result.count;
            this.selectedEquipmentTracking.equipment.price = result.equipment.price;
            this.selectedEquipmentTracking.equipment.type = result.equipment.type;
            console.log('Profil nakon dodjele result: ')
            console.log(this.selectedEquipmentTracking)
            this.router.navigate(['/companyProfile/' + this.user.id]);
            this.snackBar.open('Succesfully edited', 'Close', {
              duration: 5000
            });
          },
          error: () => {
            console.log(console.error());
          }
        })
    }
    else{
      this.snackBar.open('All fields must be entered correctly!', 'Close', {
        duration: 5000
      });
    }
  
  
  }
}
