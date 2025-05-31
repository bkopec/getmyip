import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IpCard } from './ip-card';

describe('IpCard', () => {
  let component: IpCard;
  let fixture: ComponentFixture<IpCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IpCard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IpCard);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
