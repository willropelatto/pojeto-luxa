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
  ...TransfermarketRoutes
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}