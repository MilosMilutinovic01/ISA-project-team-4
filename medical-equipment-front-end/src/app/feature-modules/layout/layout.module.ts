import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { StakeholderModule } from '../stakeholder/stakeholder.module';

@NgModule({
  declarations: [HomeComponent, NavbarComponent],
  imports: [CommonModule, StakeholderModule],
  exports: [NavbarComponent],
})
export class LayoutModule {}
