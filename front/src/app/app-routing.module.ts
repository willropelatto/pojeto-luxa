import { RegisterComponent } from './user/cadastrar/user-cadastrar.component';
import { LoginComponent } from './user/login/login.component';
/**
 * Arquivo principal de rotas e navegação da aplicação.
 *
 * @author
 * @since 0.0.3
 */

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PlayerRoutes } from './player';

import { LeagueRoutes } from './league'; 
import { TeamRoutes } from './team';

import { BidinfoRoutes } from './bidinfo';

import { TransfermarketRoutes } from './transfermarket';


export const routes: Routes = [
  ...PlayerRoutes,
  ...LeagueRoutes,
  ...TeamRoutes,
  ...BidinfoRoutes,
  ...TransfermarketRoutes,
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}