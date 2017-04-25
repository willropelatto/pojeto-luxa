/**
 * Arquivo de configuração do módulo.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { 
	BidinfoCadastrarComponent,
	BidinfoEditarComponent,
	BidinfoListarComponent,
	BidinfoVisualizarComponent,
	BidinfoService
} from './';

import { SharedModule } from '../shared';

@NgModule({
	imports: [ 
		RouterModule,
		SharedModule
	],
	declarations: [
		BidinfoCadastrarComponent,
		BidinfoEditarComponent,
		BidinfoListarComponent,
		BidinfoVisualizarComponent
	],
	providers: [
		BidinfoService
	]
})
export class BidinfoModule {}
