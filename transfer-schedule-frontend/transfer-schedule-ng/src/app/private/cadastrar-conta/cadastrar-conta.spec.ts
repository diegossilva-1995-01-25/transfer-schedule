import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastrarConta } from './cadastrar-conta';

describe('CadastrarConta', () => {
  let component: CadastrarConta;
  let fixture: ComponentFixture<CadastrarConta>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CadastrarConta],
    }).compileComponents();

    fixture = TestBed.createComponent(CadastrarConta);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
