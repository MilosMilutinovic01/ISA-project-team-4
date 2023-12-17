import { Component } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { StakeholderService } from '../stakeholder.service';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';
import { ActivatedRoute } from '@angular/router';
import { Item } from 'src/app/shared/model/item.model';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent {
  items : Item[] = [];

  firstFormGroup = this._formBuilder.group({
    firstCtrl: ['', Validators.required],
  });
  secondFormGroup = this._formBuilder.group({
    secondCtrl: ['', Validators.required],
  });

  constructor(private _formBuilder: FormBuilder,
    private service: StakeholderService,
    private authService: AuthService,
    private route: ActivatedRoute) {}

  ngOnInit():void{
    this.route.params.subscribe((params) => {
      
      this.service.getItemsByCustomerId(this.authService.getCurrentUserId().toString()).subscribe({
        next: (result) => {
          
          for(let i of result){
            if(i.companyId === params['id']){
              this.items.push(i);
            }
          }
          console.log("ITEMS: ",this.items);
        },
        error: () => {
          console.log(console.error);
        },
      });
    });
  }
}
