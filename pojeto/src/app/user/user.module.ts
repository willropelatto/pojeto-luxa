/**
 * Arquivo de configuração do módulo.
 *
 * @author Pojeto
 * @since 0.0.0
 */

import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AuthenticationService } from './shared/authentication.service';

import { 
} from './';
 
import { SharedModule } from '../shared';

@NgModule({
	imports: [ 
		RouterModule,
		SharedModule
	],
	declarations: [

	],
	providers: [
		AuthenticationService

	]
})
export class UserModule {}
