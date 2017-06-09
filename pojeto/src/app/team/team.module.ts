import { UserService } from './../user/shared/user.service';
import { ReactiveFormsModule } from '@angular/forms';
import { MdOptionModule, MdSelectModule, MaterialModule, MdNativeDateModule } from '@angular/material';
/**
 * Arquivo de configuração do módulo.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

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
		MdOptionModule,
		MdSelectModule,
		MaterialModule,
        MdNativeDateModule,
        ReactiveFormsModule
	],
	declarations: [
		TeamCadastrarComponent,
		TeamEditarComponent,
		TeamListarComponent,
		TeamVisualizarComponent
	],
	providers: [
		TeamService, UserService
	]
})
export class TeamModule {}
