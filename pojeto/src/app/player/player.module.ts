/**
 * Arquivo de configuração do módulo.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CommonModule } from '@angular/common';

import { PaginationModule } from 'ngx-bootstrap';
import { TabsModule } from 'ngx-bootstrap';

import { Ng2TableModule } from 'ng2-table/ng2-table';

import { Ng2BootstrapModule} from 'ngx-bootstrap';

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
		SharedModule,
	    PaginationModule,
		TabsModule,
		CommonModule,
		Ng2TableModule,
		Ng2BootstrapModule
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
