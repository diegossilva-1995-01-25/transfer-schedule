import { Component, inject, signal } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { NgClass } from '@angular/common';
import { TelaService } from './services/tela.services';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NgClass],
  templateUrl: './app.html',
  styleUrls: ['./app.scss']
})
export class App {
  protected readonly title = signal('transfer-schedule-ng');
  private router = inject(Router);
  private telaService = inject(TelaService);
  public telaAtual = this.telaService.telaAtual;

  redirectLogin(): void {
    this.router.navigate(['/login']);
    this.telaService.setTela('login');
    localStorage.removeItem('idConta');
    localStorage.removeItem('authToken');
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