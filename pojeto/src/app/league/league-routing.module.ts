import { AuthGuard } from './../guards/auth.guard';
/**
 * Arquivo de configuração de rotas.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Routes, RouterModule } from '@angular/router'; 

import { 
	LeagueListarComponent,
	LeagueCadastrarComponent,
	LeagueEditarComponent,
	LeagueVisualizarComponent
} from './';

export const LeagueRoutes: Routes = [
	{ path: 'leagues', redirectTo: 'leagues/listar', canActivate: [AuthGuard] },
	{ path: 'leagues/listar', component: LeagueListarComponent, canActivate: [AuthGuard] }, 
	{ path: 'leagues/cadastrar', component: LeagueCadastrarComponent, canActivate: [AuthGuard] }, 
	{ path: 'leagues/editar/:id', component: LeagueEditarComponent, canActivate: [AuthGuard] },
	{ path: 'leagues/visualizar/:id', component: LeagueVisualizarComponent, canActivate: [AuthGuard] }
];
