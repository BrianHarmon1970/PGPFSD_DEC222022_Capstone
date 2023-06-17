import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PortfolioLandingComponent } from './portfolio-landing.component';

describe('PortfolioLandingComponent', () => {
  let component: PortfolioLandingComponent;
  let fixture: ComponentFixture<PortfolioLandingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PortfolioLandingComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PortfolioLandingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
