/**
 * Arquivo de configuração de rotas.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Routes } from '@angular/router'; 

import { 
	BidinfoListarComponent,
	BidinfoCadastrarComponent,
	BidinfoEditarComponent,
	BidinfoVisualizarComponent
} from './';

export const BidinfoRoutes: Routes = [
	{ path: 'bidinfos', redirectTo: 'bidinfos/listar' },
	{ path: 'bidinfos/listar', component: BidinfoListarComponent }, 
	{ path: 'bidinfos/cadastrar', component: BidinfoCadastrarComponent }, 
	{ path: 'bidinfos/editar/:id', component: BidinfoEditarComponent },
	{ path: 'bidinfos/visualizar/:id', component: BidinfoVisualizarComponent }
];
