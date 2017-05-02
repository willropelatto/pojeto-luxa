/**
 * Arquivo de configuração do módulo.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';

import { PaginationModule } from 'ngx-bootstrap';
import { TabsModule } from 'ngx-bootstrap';

import { Ng2TableModule } from 'ng2-table/ng2-table';

import { Ng2BootstrapModule} from 'ngx-bootstrap';

import { 
	TeamCadastrarComponent,
	TeamEditarComponent,
	TeamListarComponent,
	TeamVisualizarComponent,
	TeamService
} from './';

import { SharedModule } from '../shared';

@NgModule({
	imports: [ 
		RouterModule,
		SharedModule,
		CommonModule,
		PaginationModule,
		TabsModule,
		Ng2TableModule,
	],
	declarations: [
		TeamCadastrarComponent,
		TeamEditarComponent,
		TeamListarComponent,
		TeamVisualizarComponent
	],
	providers: [
		TeamService
	]
})
export class TeamModule {}
