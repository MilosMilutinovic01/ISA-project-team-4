import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdministratorRegistrationComponent } from './administrator-registration.component';

describe('AdministratorRegistrationComponent', () => {
  let component: AdministratorRegistrationComponent;
  let fixture: ComponentFixture<AdministratorRegistrationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdministratorRegistrationComponent]
    });
    fixture = TestBed.createComponent(AdministratorRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
