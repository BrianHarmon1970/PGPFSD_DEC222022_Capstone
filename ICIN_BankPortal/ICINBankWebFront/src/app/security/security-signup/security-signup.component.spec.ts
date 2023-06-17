import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SecuritySignupComponent } from './security-signup.component';

describe('SecuritySignupComponent', () => {
  let component: SecuritySignupComponent;
  let fixture: ComponentFixture<SecuritySignupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SecuritySignupComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SecuritySignupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
