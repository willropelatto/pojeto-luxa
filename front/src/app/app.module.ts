/**
 * Arquivo de módulo principal da aplicação.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent }  from './app.component';
import { AppRoutingModule }   from './app-routing.module';
import { PlayerModule} from './player';
import { LeagueModule} from './league';
import { TeamModule } from './team';

import { TransfermarketModule } from './transfermarket';

import { BidinfoModule } from './bidinfo';

import { HttpModule } from '@angular/http';
import { HttpUtilService } from './util';


import { PaginationModule } from 'ngx-bootstrap';
import { TabsModule } from 'ngx-bootstrap';


@NgModule({
  imports:      [
    BrowserModule,
    AppRoutingModule,
    PlayerModule,
    LeagueModule,
    TeamModule,
    TransfermarketModule,
    BidinfoModule,
    HttpModule,
    PaginationModule,
		TabsModule,
  ],
  declarations: [ 
    AppComponent
  ],
  providers: [
		HttpUtilService    
  ],
  bootstrap:    [ AppComponent ]
})
export class AppModule {}
