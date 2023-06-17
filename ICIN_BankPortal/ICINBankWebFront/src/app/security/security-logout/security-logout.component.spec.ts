import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SecurityLogoutComponent } from './security-logout.component';

describe('SecurityLogoutComponent', () => {
  let component: SecurityLogoutComponent;
  let fixture: ComponentFixture<SecurityLogoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SecurityLogoutComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SecurityLogoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
