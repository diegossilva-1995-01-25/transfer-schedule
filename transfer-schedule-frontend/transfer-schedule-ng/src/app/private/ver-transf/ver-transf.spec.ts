import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerTransf } from './ver-transf';

describe('VerTransf', () => {
  let component: VerTransf;
  let fixture: ComponentFixture<VerTransf>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VerTransf],
    }).compileComponents();

    fixture = TestBed.createComponent(VerTransf);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
