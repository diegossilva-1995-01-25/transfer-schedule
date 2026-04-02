import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Transf } from './transf';

describe('Transf', () => {
  let component: Transf;
  let fixture: ComponentFixture<Transf>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Transf],
    }).compileComponents();

    fixture = TestBed.createComponent(Transf);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
