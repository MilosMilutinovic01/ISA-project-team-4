import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EquipmentPickupQrComponent } from './equipment-pickup-qr.component';

describe('EquipmentPickupQrComponent', () => {
  let component: EquipmentPickupQrComponent;
  let fixture: ComponentFixture<EquipmentPickupQrComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EquipmentPickupQrComponent]
    });
    fixture = TestBed.createComponent(EquipmentPickupQrComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
