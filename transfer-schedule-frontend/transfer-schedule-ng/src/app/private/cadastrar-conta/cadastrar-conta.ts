import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TelaService } from '../../services/tela.services';

@Component({
  selector: 'app-cadastrar-conta',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HttpClientModule],
  templateUrl: './cadastrar-conta.html',
  styleUrl: './cadastrar-conta.scss',
})
export class CadastrarConta {
  private router = inject(Router);
  private telaService = inject(TelaService);
  private fb = inject(FormBuilder);
  private http = inject(HttpClient);

  public telaAtual = this.telaService.telaAtual;

  public form = this.fb.group(
    {
      saldo: ['', [Validators.required]],
    },
  );

  redirectContas(): void {
    this.router.navigate(['/contas']);
    this.telaService.setTela('contas');
  }

  onSubmit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const payload = {
      saldo: this.form.value.saldo,
      
    };

    const url = 'http://192.168.18.3:8080/transf-sched/conta/cadastrar';

    this.http.post(url, payload, {
      headers: {
        "Authorization": 'Bearer ' + localStorage.getItem('authToken')
      }}).subscribe({
      next: (response) => {

        console.log('Registration success', response);
        this.redirectContas();

      },
      error: (err) => {

        console.error('Registration error', err);

      },
    });
  }

}
