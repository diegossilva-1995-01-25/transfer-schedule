import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerAllTransf } from './ver-all-transf';

describe('VerAllTransf', () => {
  let component: VerAllTransf;
  let fixture: ComponentFixture<VerAllTransf>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VerAllTransf],
    }).compileComponents();

    fixture = TestBed.createComponent(VerAllTransf);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
