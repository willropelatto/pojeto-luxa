/**
 * Arquivo de configuração do módulo.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { 
	LeagueCadastrarComponent,
	LeagueEditarComponent,
	LeagueListarComponent,
	LeagueVisualizarComponent,
	LeagueService
} from './';

import { SharedModule } from '../shared';

@NgModule({
	imports: [ 
		RouterModule,
		SharedModule
	],
	declarations: [
		LeagueCadastrarComponent,
		LeagueEditarComponent,
		LeagueListarComponent,
		LeagueVisualizarComponent
	],
	providers: [
		LeagueService
	]
})
export class LeagueModule {}
