/**
 * Arquivo de configuração de rotas.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Routes } from '@angular/router'; 

import { 
	TeamListarComponent,
	TeamCadastrarComponent,
	TeamEditarComponent,
	TeamVisualizarComponent
} from './';

export const TeamRoutes: Routes = [
	{ path: 'teams', redirectTo: 'teams/listar' },
	{ path: 'teams/listar', component: TeamListarComponent }, 
	{ path: 'teams/cadastrar', component: TeamCadastrarComponent }, 
	{ path: 'teams/editar/:id', component: TeamEditarComponent },
	{ path: 'teams/visualizar/:id', component: TeamVisualizarComponent }
];
