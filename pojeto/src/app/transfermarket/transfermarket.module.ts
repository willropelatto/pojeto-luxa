import { TransfermarketNameFilter, TransfermarketPositionFilter } from './shared/transfermarket.pipe';
import { TransfermarketFilterComponent } from './filtro/transfermarket-filter.component';
import { MdOptionModule, MdSelectModule } from '@angular/material';
import { AlertService } from './../util/alert.service';
import { TeamModule } from './../team/team.module';
import { TeamService } from './../team/shared/team.service';
import {NgxPaginationModule} from 'ngx-pagination';
/**
 * Arquivo de configuração do módulo.
 *
 * @author Pojeto
 * @since 0.0.1
 */

import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlayerModule, PlayerService, Player } from '../player';

import { BidinfoService, BidinfoModule } from '../bidinfo';

import {SelectModule} from 'ng2-select';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule, MdNativeDateModule } from '@angular/material';

import { 
	TransfermarketCadastrarComponent,
	TransfermarketEditarComponent,
	TransfermarketListarComponent,
	TransfermarketVisualizarComponent,
	TransfermarketService
} from './';

import { SharedModule } from '../shared';

@NgModule({
	imports: [ 
		RouterModule,
		SharedModule,
		PlayerModule,
		BidinfoModule,
		TeamModule,
		SelectModule,
		MdOptionModule,
		MdSelectModule,
		MaterialModule,
        MdNativeDateModule,
        ReactiveFormsModule,
		NgxPaginationModule
	],
	declarations: [
		TransfermarketCadastrarComponent,
		TransfermarketEditarComponent,
		TransfermarketListarComponent,
		TransfermarketVisualizarComponent,
		TransfermarketFilterComponent,
		TransfermarketNameFilter,
		TransfermarketPositionFilter
	],	
	providers: [
		TransfermarketService, TeamService, BidinfoService, PlayerService, AlertService
	]
})
export class TransfermarketModule {}
