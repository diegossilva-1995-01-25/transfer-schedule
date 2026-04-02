import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { TelaService } from '../../services/tela.services';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-ver-all-transf',
  imports: [],
  templateUrl: './ver-all-transf.html',
  styleUrl: './ver-all-transf.scss',
})
export class VerAllTransf {
  private router = inject(Router);
  private telaService = inject(TelaService);
  public transfs: any;
  private http = inject(HttpClient);
  private idConta = localStorage.getItem('idConta');

  ngOnInit() {

    const url = 'http://192.168.18.3:8080/transf-sched/transf/todos-origem/' + this.idConta;

    this.http.get(url, {
      headers: {
        "Authorization": 'Bearer ' + localStorage.getItem('authToken')
      }
    }).subscribe({
      next: (response) => {

        this.transfs = response;

      },
      error: (err) => {

        console.error('Error on fetch transfs', err);

      },
    });

  }

  redirectMenu(): void {
    this.router.navigate(['/menu']);
    this.telaService.setTela('menu');
  }

  redirectTransfPlus(): void {
    this.router.navigate(['/transf']);
    this.telaService.setTela('transf');
  }

}
