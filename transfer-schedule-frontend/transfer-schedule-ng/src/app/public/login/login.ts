import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { TelaService } from '../../services/tela.services';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HttpClientModule],
  templateUrl: './login.html',
  styleUrls: ['./login.scss'],
})
export class Login {
  private router = inject(Router);
  private telaService = inject(TelaService);
  private fb = inject(FormBuilder);
  private http = inject(HttpClient);

  public telaAtual = this.telaService.telaAtual;
  public errorMessage: string | null = null;

  public form = this.fb.group({
    cpf: ['', [Validators.required]],
    senha: ['', [Validators.required]],
  });

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
    this.errorMessage = null;
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const payload = {
      cpf: this.form.value.cpf,
      senha: this.form.value.senha,
    };

    const url = 'http://192.168.18.3:8080/transf-sched/api/auth/login';

    this.http.post<{ token?: string; user?: any }>(url, payload).subscribe({
      next: (res) => {
        console.log('Login success', res);
        if (res && res.token) {
          // Save token for later requests; consider using a dedicated AuthService
          localStorage.setItem('authToken', res.token);
        }
        // navigate to app main screen
        this.redirectMenu();
      },
      error: (err) => {
        console.error('Login error', err);
        // Friendly message for UI
        this.errorMessage = err?.error?.message || 'Login failed. Check CPF and senha.';
      },
    });
  }
}