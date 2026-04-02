import { Injectable, signal, WritableSignal } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class TelaService {
  public telaAtual: WritableSignal<string> = signal('login');

  setTela(tela: string): void {
    this.telaAtual.set(tela);
  }

  getTela(): WritableSignal<string> {
    return this.telaAtual;
  }
  
}