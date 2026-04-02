import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { TelaService } from '../../services/tela.services';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.html',
  styleUrls: ['./login.scss'],
})
export class Login {
  private router = inject(Router);
  private telaService = inject(TelaService);
  public telaAtual = this.telaService.telaAtual;

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
}