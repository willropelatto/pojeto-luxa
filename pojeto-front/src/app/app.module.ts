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

import { DemoModule } from './';

import { PlayerModule } from './player';

import { LeagueModule } from './league';

@NgModule({
  imports:      [
    BrowserModule,
    AppRoutingModule,
    PlayerModule,
    LeagueModule
  ],
  declarations: [ 
    AppComponent
  ],
  bootstrap:    [ AppComponent ]
})
export class AppModule {}
