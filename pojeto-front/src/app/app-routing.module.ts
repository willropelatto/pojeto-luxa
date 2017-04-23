/**
 * Arquivo principal de rotas e navegação da aplicação.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PlayerRoutes } from './player';

import { LeagueRoutes } from './league';


export const routes: Routes = [
  ...PlayerRoutes,
  ...LeagueRoutes,
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}