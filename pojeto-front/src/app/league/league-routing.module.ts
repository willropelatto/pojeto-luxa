/**
 * Arquivo de configuração de rotas.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Routes } from '@angular/router'; 

import { 
	LeagueListarComponent,
	LeagueCadastrarComponent,
	LeagueEditarComponent,
	LeagueVisualizarComponent
} from './';

export const LeagueRoutes: Routes = [
	{ path: 'leagues', redirectTo: 'leagues/listar' },
	{ path: 'leagues/listar', component: LeagueListarComponent }, 
	{ path: 'leagues/cadastrar', component: LeagueCadastrarComponent }, 
	{ path: 'leagues/editar/:id', component: LeagueEditarComponent },
	{ path: 'leagues/visualizar/:id', component: LeagueVisualizarComponent }
];
