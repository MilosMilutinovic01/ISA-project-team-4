import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  searchForEquipment: boolean = true;

  toggleSearchMode() {
    this.searchForEquipment = !this.searchForEquipment;
  }
}
