import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompanyAdministartorRegistrationComponent } from './company-administartor-registration.component';

describe('CompanyAdministartorRegistrationComponent', () => {
  let component: CompanyAdministartorRegistrationComponent;
  let fixture: ComponentFixture<CompanyAdministartorRegistrationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CompanyAdministartorRegistrationComponent]
    });
    fixture = TestBed.createComponent(CompanyAdministartorRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
