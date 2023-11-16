import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { LayoutModule } from './feature-modules/layout/layout.module';
import { AuthModule } from './infrastructure/auth/auth.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './infrastructure/auth/login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RoutingModule } from './infrastructure/routing/routing.module';
import { HttpClientModule } from '@angular/common/http';
import { MaterialModule } from './infrastructure/material/material.module';
import { StakeholderModule } from './feature-modules/stakeholder/stakeholder.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';


@NgModule({
  declarations: [AppComponent, LoginComponent],
  imports: [
    BrowserModule,
    LayoutModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    RoutingModule,
    HttpClientModule,
    AuthModule,
    MaterialModule,
    StakeholderModule,
    FontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
