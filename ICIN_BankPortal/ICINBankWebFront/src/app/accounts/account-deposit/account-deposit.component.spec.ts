import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountDepositComponent } from './account-deposit.component';

describe('AccountDepositComponent', () => {
  let component: AccountDepositComponent;
  let fixture: ComponentFixture<AccountDepositComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccountDepositComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AccountDepositComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
