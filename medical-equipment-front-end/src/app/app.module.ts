import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { LayoutModule } from './feature-modules/layout/layout.module';
import { AuthModule } from './infrastructure/auth/auth.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './infrastructure/auth/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RoutingModule } from './infrastructure/routing/routing.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MaterialModule } from './infrastructure/material/material.module';
import { StakeholderModule } from './feature-modules/stakeholder/stakeholder.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { JwtInterceptor } from './infrastructure/auth/jwt/jwt.interceptor';
import { MapComponent } from './shared/map/map.component';
import { AuthService } from '@auth0/auth0-angular';

@NgModule({
  declarations: [AppComponent, LoginComponent, MapComponent],
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
    FontAwesomeModule,
    FormsModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true,
    },
    AuthService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
