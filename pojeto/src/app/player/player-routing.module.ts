import { AuthGuard } from './../guards/auth.guard';
/**
 * Arquivo de configuração de rotas.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Routes, RouterModule } from '@angular/router'; 

import { 
	PlayerListarComponent,
	PlayerCadastrarComponent,
	PlayerEditarComponent,
	PlayerVisualizarComponent
} from './';

export const PlayerRoutes: Routes = [
	{ path: 'players', redirectTo: 'players/listar', canActivate: [AuthGuard] },
	{ path: 'players/listar', component: PlayerListarComponent, canActivate: [AuthGuard] }, 
	{ path: 'players/cadastrar', component: PlayerCadastrarComponent, canActivate: [AuthGuard] }, 
	{ path: 'players/editar/:id', component: PlayerEditarComponent, canActivate: [AuthGuard] },
	{ path: 'players/visualizar/:id', component: PlayerVisualizarComponent, canActivate: [AuthGuard] }
];
