import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { TelaService } from '../../services/tela.services';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-menu',
  imports: [CommonModule, ReactiveFormsModule, HttpClientModule],
  templateUrl: './menu.html',
  styleUrl: './menu.scss',
})
export class Menu {
  private router = inject(Router);
  private telaService = inject(TelaService);
  private fb = inject(FormBuilder);
  private http = inject(HttpClient);
  public conta: any;

  redirectContas(): void {
    this.router.navigate(['/contas']);
    this.telaService.setTela('contas');
  }

  redirectContaPlus(): void {
    this.router.navigate(['/cadastrar-conta']);
    this.telaService.setTela('cadastrar-conta');
  }

  redirectTransferencias(): void {
    this.router.navigate(['/ver-all-transf']);
    this.telaService.setTela('ver-all-transf');
  }

  ngOnInit() {

    const url = 'http://192.168.18.3:8080/transf-sched/conta/1';

    this.http.get(url, {
      headers: {
        "Authorization": 'Bearer ' + localStorage.getItem('authToken')
      }
    }).subscribe({
      next: (response) => {

        this.conta = response;
        localStorage.setItem('idConta', this.conta.id);

      },
      error: (err) => {

        console.error('Error on fetch conta', err);
        this.redirectContaPlus();

      },
    });

  }

}
