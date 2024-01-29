import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerScheduledAppointmentsComponent } from './customer-scheduled-appointments.component';

describe('CustomerScheduledAppointmentsComponent', () => {
  let component: CustomerScheduledAppointmentsComponent;
  let fixture: ComponentFixture<CustomerScheduledAppointmentsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CustomerScheduledAppointmentsComponent]
    });
    fixture = TestBed.createComponent(CustomerScheduledAppointmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
