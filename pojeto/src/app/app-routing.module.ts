import { TeamVisualizarComponent } from './team/visualizar/team-visualizar.component';
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

import { UserRoutes } from './user';

export const routes: Routes = [
  ...TeamRoutes,
  ...PlayerRoutes,
  ...LeagueRoutes,
  ...BidinfoRoutes,
  ...TransfermarketRoutes,
  { path: '', component: TeamVisualizarComponent},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}