import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectIrregularAppointmentDialogComponent } from './select-irregular-appointment-dialog.component';

describe('SelectIrregularAppointmentDialogComponent', () => {
  let component: SelectIrregularAppointmentDialogComponent;
  let fixture: ComponentFixture<SelectIrregularAppointmentDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SelectIrregularAppointmentDialogComponent]
    });
    fixture = TestBed.createComponent(SelectIrregularAppointmentDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
