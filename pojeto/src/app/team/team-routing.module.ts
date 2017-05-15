import { AuthGuard } from './../guards/auth.guard';
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
	{ path: 'teams', redirectTo: 'teams/listar', canActivate: [AuthGuard] },
	{ path: 'teams/listar', component: TeamListarComponent, canActivate: [AuthGuard] }, 
	{ path: 'teams/cadastrar', component: TeamCadastrarComponent, canActivate: [AuthGuard] }, 
	{ path: 'teams/editar/:id', component: TeamEditarComponent, canActivate: [AuthGuard]  },
	{ path: 'teams/visualizar/:id', component: TeamVisualizarComponent, canActivate: [AuthGuard] }
];
