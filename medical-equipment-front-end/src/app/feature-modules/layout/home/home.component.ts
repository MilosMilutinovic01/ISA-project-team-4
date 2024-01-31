import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';
import { User } from 'src/app/infrastructure/auth/model/user.model';
import { StakeholderService } from '../../stakeholder/stakeholder.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  searchForEquipment: boolean = true;
  user: any;

  constructor(private service: StakeholderService, private authService: AuthService, public router: Router) { }

  ngOnInit(): void {
    this.authService.user$.subscribe((user) => {
      this.user = user;
    });
  }

  toggleSearchMode() {
    this.searchForEquipment = !this.searchForEquipment;
  }
}
