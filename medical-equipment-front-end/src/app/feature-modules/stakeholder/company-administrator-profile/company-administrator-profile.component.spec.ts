import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompanyAdministratorProfileComponent } from './company-administrator-profile.component';

describe('CompanyAdministratorProfileComponent', () => {
  let component: CompanyAdministratorProfileComponent;
  let fixture: ComponentFixture<CompanyAdministratorProfileComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CompanyAdministratorProfileComponent]
    });
    fixture = TestBed.createComponent(CompanyAdministratorProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
