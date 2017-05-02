/**
 * Arquivo de configuração do módulo.
 *
 * @author Pojeto
 * @since 0.0.1
 */

import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlayerModule, PlayerService, Player } from '../player';

import { BidinfoService, BidinfoModule } from '../bidinfo';

import { 
	TransfermarketCadastrarComponent,
	TransfermarketEditarComponent,
	TransfermarketListarComponent,
	TransfermarketVisualizarComponent,
	TransfermarketService
} from './';

import { SharedModule } from '../shared';

@NgModule({
	imports: [ 
		RouterModule,
		SharedModule,
		PlayerModule,
		BidinfoModule
	],
	declarations: [
		TransfermarketCadastrarComponent,
		TransfermarketEditarComponent,
		TransfermarketListarComponent,
		TransfermarketVisualizarComponent
	],
	providers: [
		TransfermarketService, PlayerService, BidinfoService
	]
})
export class TransfermarketModule {}
