import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditCompanyAdministratorProfileComponent } from './edit-company-administrator-profile.component';

describe('EditCompanyAdministratorProfileComponent', () => {
  let component: EditCompanyAdministratorProfileComponent;
  let fixture: ComponentFixture<EditCompanyAdministratorProfileComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditCompanyAdministratorProfileComponent]
    });
    fixture = TestBed.createComponent(EditCompanyAdministratorProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
