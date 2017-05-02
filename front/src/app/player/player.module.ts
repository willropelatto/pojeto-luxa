/**
 * Arquivo de configuração do módulo.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { 
	PlayerCadastrarComponent,
	PlayerEditarComponent,
	PlayerListarComponent,
	PlayerVisualizarComponent,
	PlayerService
} from './';

import { SharedModule } from '../shared';

@NgModule({
	imports: [ 
		RouterModule,
		SharedModule
	],
	declarations: [
		PlayerCadastrarComponent,
		PlayerEditarComponent,
		PlayerListarComponent,
		PlayerVisualizarComponent
	],
	providers: [
		PlayerService
	],
	exports: [
		PlayerCadastrarComponent
	]

})
export class PlayerModule {}
