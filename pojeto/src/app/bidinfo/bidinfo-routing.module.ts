
/**
 * Arquivo de configuração de rotas.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */


import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './../guards/auth.guard';

import { 
	BidinfoListarComponent,
	BidinfoCadastrarComponent,
	BidinfoEditarComponent,
	BidinfoVisualizarComponent
} from './';

export const BidinfoRoutes: Routes = [
	{ path: 'bidinfos', redirectTo: 'bidinfos/listar', canActivate: [AuthGuard]},
	{ path: 'bidinfos/listar', component: BidinfoListarComponent, canActivate: [AuthGuard] }, 
	{ path: 'bidinfos/cadastrar', component: BidinfoCadastrarComponent, canActivate: [AuthGuard] }, 
	{ path: 'bidinfos/editar/:id', component: BidinfoEditarComponent, canActivate: [AuthGuard] },
	{ path: 'bidinfos/visualizar/:id', component: BidinfoVisualizarComponent, canActivate: [AuthGuard] }
];
