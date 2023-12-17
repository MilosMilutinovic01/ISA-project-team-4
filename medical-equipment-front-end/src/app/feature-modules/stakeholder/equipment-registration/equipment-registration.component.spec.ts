import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EquipmentRegistrationComponent } from './equipment-registration.component';

describe('EquipmentRegistrationComponent', () => {
  let component: EquipmentRegistrationComponent;
  let fixture: ComponentFixture<EquipmentRegistrationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EquipmentRegistrationComponent]
    });
    fixture = TestBed.createComponent(EquipmentRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
