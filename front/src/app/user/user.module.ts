/**
 * Arquivo de configuração do módulo.
 *
 * @author Pojeto
 * @since 0.0.0
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
	UserCadastrarComponent,
	UserEditarComponent,
	UserListarComponent,
	UserVisualizarComponent,
	UserService
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
		UserCadastrarComponent,
		UserEditarComponent,
		UserListarComponent,
		UserVisualizarComponent
	],
	providers: [
		UserService
	]
})
export class UserModule {}
