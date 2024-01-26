import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerQrCodesComponent } from './customer-qr-codes.component';

describe('CustomerQrCodesComponent', () => {
  let component: CustomerQrCodesComponent;
  let fixture: ComponentFixture<CustomerQrCodesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CustomerQrCodesComponent]
    });
    fixture = TestBed.createComponent(CustomerQrCodesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
