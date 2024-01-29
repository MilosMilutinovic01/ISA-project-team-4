import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerPickupHistoryComponent } from './customer-pickup-history.component';

describe('CustomerPickupHistoryComponent', () => {
  let component: CustomerPickupHistoryComponent;
  let fixture: ComponentFixture<CustomerPickupHistoryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CustomerPickupHistoryComponent]
    });
    fixture = TestBed.createComponent(CustomerPickupHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
