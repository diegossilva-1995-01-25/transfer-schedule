import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { TelaService } from '../../services/tela.services';
import { HttpClient, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-registrar',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HttpClientModule],
  templateUrl: './registrar.html',
  styleUrls: ['./registrar.scss'],
})
export class Registrar {
  private router = inject(Router);
  private telaService = inject(TelaService);
  private fb = inject(FormBuilder);
  private http = inject(HttpClient);

  public telaAtual = this.telaService.telaAtual;

  public form = this.fb.group(
    {
      nome: ['', [Validators.required]],
      cpf: ['', [Validators.required]],
      senha: ['', [Validators.required, Validators.minLength(6)]],
      confSenha: ['', [Validators.required]],
    },
    { validators: this.passwordsMatchValidator }
  );

  private passwordsMatchValidator(group: any) {
    const senha = group.get('senha')?.value;
    const conf = group.get('confSenha')?.value;
    return senha === conf ? null : { passwordsMismatch: true };
  }

  redirectLogin(): void {
    this.router.navigate(['/login']);
    this.telaService.setTela('login');
  }

  redirectRegistrar(): void {
    this.router.navigate(['/registrar']);
    this.telaService.setTela('registrar');
  }

  redirectMenu(): void {
    this.router.navigate(['/menu']);
    this.telaService.setTela('menu');
  }

  onSubmit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const payload = {
      nome: this.form.value.nome,
      cpf: this.form.value.cpf,
      senha: this.form.value.senha,
      
    };

    const url = 'http://192.168.18.3:8080/transf-sched/api/auth/registrar';

    this.http.post(url, payload).subscribe({
      next: (response) => {

        console.log('Registration success', response);
        this.redirectLogin();

      },
      error: (err) => {

        console.error('Registration error', err);

      },
    });
  }

}