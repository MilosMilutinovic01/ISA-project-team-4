import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditEquipmentTrackingComponent } from './edit-equipment-tracking.component';

describe('EditEquipmentTrackingComponent', () => {
  let component: EditEquipmentTrackingComponent;
  let fixture: ComponentFixture<EditEquipmentTrackingComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditEquipmentTrackingComponent]
    });
    fixture = TestBed.createComponent(EditEquipmentTrackingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
