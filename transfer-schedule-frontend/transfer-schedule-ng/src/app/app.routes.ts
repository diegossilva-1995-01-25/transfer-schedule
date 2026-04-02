import { Routes } from '@angular/router';

import { Login } from './public/login/login';
import { Registrar } from './public/registrar/registrar';

import { Menu } from './private/menu/menu';
import { Conta } from './private/conta/conta';
import { Contas } from './private/contas/contas';
import { Transf } from './private/transf/transf';
import { CadastrarConta } from './private/cadastrar-conta/cadastrar-conta';
import { VerTransf } from './private/ver-transf/ver-transf';
import { VerAllTransf } from './private/ver-all-transf/ver-all-transf';

//import { AuthGuard } from './guards/auth.guard'; // implement this to protect private routes

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },

  // Public pages
  { path: 'login', component: Login },
  { path: 'registrar', component: Registrar },
  { path: '', redirectTo: 'login', pathMatch: 'full' },

  // Private area (menu is the layout; children are the private pages)
  {
    path: 'app',               // or use 'private'
    component: Menu,
    //canActivate: [AuthGuard], // optional: only if you have an AuthGuard
    children: [
      { path: '', redirectTo: 'contas', pathMatch: 'full' },
      { path: 'conta', component: Conta },
      { path: 'contas', component: Contas },
      { path: 'transf', component: Transf },
      { path: 'cadastrar-conta', component: CadastrarConta },
      { path: 'ver-transf/:id', component: VerTransf }, // example param
      { path: 'ver-all-transf', component: VerAllTransf }
    ]
  },

  // Wildcard
  { path: '**', redirectTo: 'login' }
];