import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { TelaService } from '../../services/tela.services';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-contas',
  imports: [CommonModule, ReactiveFormsModule, HttpClientModule],
  templateUrl: './contas.html',
  styleUrl: './contas.scss',
})
export class Contas {

  private router = inject(Router);
  private telaService = inject(TelaService);
  public contas: any;
  private http = inject(HttpClient);

  ngOnInit() {

    const url = 'http://192.168.18.3:8080/transf-sched/conta/todos';

    this.http.get(url, {
      headers: {
        "Authorization": 'Bearer ' + localStorage.getItem('authToken')
      }
    }).subscribe({
      next: (response) => {

        this.contas = response;

      },
      error: (err) => {

        console.error('Error on fetch contas', err);

      },
    });

  }

  redirectMenu(): void {
    this.router.navigate(['/menu']);
    this.telaService.setTela('menu');
  }

  redirectContaPlus(): void {
    this.router.navigate(['/cadastrar-conta']);
    this.telaService.setTela('cadastrar-conta');
  }

}
