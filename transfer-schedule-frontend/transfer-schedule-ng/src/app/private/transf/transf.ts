import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TelaService } from '../../services/tela.services';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-transf',
  imports: [CommonModule, ReactiveFormsModule, HttpClientModule],
  templateUrl: './transf.html',
  styleUrl: './transf.scss',
})
export class Transf {
  private router = inject(Router);
  private telaService = inject(TelaService);
  private fb = inject(FormBuilder);
  private http = inject(HttpClient);

  public telaAtual = this.telaService.telaAtual;

  public form = this.fb.group(
    {
      origem: [localStorage.getItem('idConta'), [Validators.required]],
      destino: ['', [Validators.required]],
      valor: ['', [Validators.required]],
      dataAgendamento: [ new Date().toISOString().slice(0, 10).replace(/-/g, ''), [Validators.required]],
      dataTransferencia: ['', [Validators.required]]
    },
  );

  redirectTransferencias(): void {
    this.router.navigate(['/ver-all-transf']);
    this.telaService.setTela('ver-all-transf');
  }

  onSubmit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const payload = {
      origem: this.form.value.origem,
      destino: this.form.value.destino,
      valor: this.form.value.valor,
      dataAgendamento: this.form.value.dataAgendamento,
      dataTransferencia: this.form.value.dataTransferencia
      
    };

    const url = 'http://192.168.18.3:8080/transf-sched/transf/agendar';

    this.http.post(url, payload, {
      headers: {
        "Authorization": 'Bearer ' + localStorage.getItem('authToken')
      }}).subscribe({
      next: (response) => {

        console.log('Registration success', response);
        this.redirectTransferencias();

      },
      error: (err) => {

        console.error('Registration error', err);

      },
    });
  }

}
