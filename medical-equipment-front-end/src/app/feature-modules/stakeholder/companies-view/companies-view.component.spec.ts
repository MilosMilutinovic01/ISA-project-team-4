import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompaniesViewComponent } from './companies-view.component';

describe('CompaniesViewComponent', () => {
  let component: CompaniesViewComponent;
  let fixture: ComponentFixture<CompaniesViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CompaniesViewComponent]
    });
    fixture = TestBed.createComponent(CompaniesViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
