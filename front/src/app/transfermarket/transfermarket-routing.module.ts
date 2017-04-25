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
	TransfermarketVisualizarComponent
} from './';

export const TransfermarketRoutes: Routes = [
	{ path: 'transfermarkets', redirectTo: 'transfermarkets/listar' },
	{ path: 'transfermarkets/listar', component: TransfermarketListarComponent }, 
	{ path: 'transfermarkets/cadastrar', component: TransfermarketCadastrarComponent }, 
	{ path: 'transfermarkets/editar/:id', component: TransfermarketEditarComponent },
	{ path: 'transfermarkets/visualizar/:id', component: TransfermarketVisualizarComponent }
];
