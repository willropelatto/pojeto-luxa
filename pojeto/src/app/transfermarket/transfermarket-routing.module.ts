
import { AuthGuard } from './../guards/auth.guard';
/**
 * Arquivo de configuração de rotas.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Routes } from '@angular/router'; 

import { 
	TransfermarketListarComponent,
	TransfermarketCadastrarComponent,
	TransfermarketEditarComponent,
	TransfermarketVisualizarComponent,
	TransfermarketFilterComponent
} from './';

export const TransfermarketRoutes: Routes = [
	{ path: 'transfermarkets', redirectTo: 'transfermarkets/listar', canActivate: [AuthGuard] },
	{ path: 'transfermarkets/listar', component: TransfermarketListarComponent, canActivate: [AuthGuard] }, 	
	{ path: 'transfermarkets/cadastrar', component: TransfermarketCadastrarComponent, canActivate: [AuthGuard] }, 
	{ path: 'transfermarkets/editar/:id', component: TransfermarketEditarComponent, canActivate: [AuthGuard] },
	{ path: 'transfermarkets/visualizar/:id', component: TransfermarketVisualizarComponent, canActivate: [AuthGuard] },
	{ path: 'transfermarkets/filtrar/:filterPlayer', component: TransfermarketFilterComponent, canActivate: [AuthGuard] },
];
