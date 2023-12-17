import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectAppointmentDialogComponent } from './select-appointment-dialog.component';

describe('SelectAppointmentDialogComponent', () => {
  let component: SelectAppointmentDialogComponent;
  let fixture: ComponentFixture<SelectAppointmentDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SelectAppointmentDialogComponent]
    });
    fixture = TestBed.createComponent(SelectAppointmentDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
