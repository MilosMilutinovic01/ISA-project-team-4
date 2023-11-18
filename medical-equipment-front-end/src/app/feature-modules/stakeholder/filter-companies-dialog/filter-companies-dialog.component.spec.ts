import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilterCompaniesDialogComponent } from './filter-companies-dialog.component';

describe('FilterCompaniesDialogComponent', () => {
  let component: FilterCompaniesDialogComponent;
  let fixture: ComponentFixture<FilterCompaniesDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FilterCompaniesDialogComponent]
    });
    fixture = TestBed.createComponent(FilterCompaniesDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
