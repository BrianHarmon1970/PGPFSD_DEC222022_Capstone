import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SecurityLogindataComponent } from './security-logindata.component';

describe('SecurityLogindataComponent', () => {
  let component: SecurityLogindataComponent;
  let fixture: ComponentFixture<SecurityLogindataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SecurityLogindataComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SecurityLogindataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
