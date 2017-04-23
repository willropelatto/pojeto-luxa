/**
 * Arquivo de configuração de rotas.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Routes } from '@angular/router'; 

import { 
	PlayerListarComponent,
	PlayerCadastrarComponent,
	PlayerEditarComponent,
	PlayerVisualizarComponent
} from './';

export const PlayerRoutes: Routes = [
	{ path: 'players', redirectTo: 'players/listar' },
	{ path: 'players/listar', component: PlayerListarComponent }, 
	{ path: 'players/cadastrar', component: PlayerCadastrarComponent }, 
	{ path: 'players/editar/:id', component: PlayerEditarComponent },
	{ path: 'players/visualizar/:id', component: PlayerVisualizarComponent }
];
