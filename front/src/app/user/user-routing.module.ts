/**
 * Arquivo de configuração de rotas.
 *
 * @author Pojeto
 * @since 0.0.0
 */

import { Routes } from '@angular/router'; 

import { 
	UserListarComponent,
	UserCadastrarComponent,
	UserEditarComponent,
	UserVisualizarComponent
} from './';

export const UserRoutes: Routes = [
	{ path: 'users', redirectTo: 'users/listar' },
	{ path: 'users/listar', component: UserListarComponent }, 
	{ path: 'users/cadastrar', component: UserCadastrarComponent }, 
	{ path: 'users/editar/:id', component: UserEditarComponent },
	{ path: 'users/visualizar/:id', component: UserVisualizarComponent }
];
