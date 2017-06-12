/**
 * Arquivo de módulo principal da aplicação.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import { AppComponent }  from './app.component';
import { AppRoutingModule }   from './app-routing.module';
import { PlayerModule} from './player';
import { LeagueModule} from './league';
import { TeamModule } from './team';

import { UserModule } from './user';

import { TransfermarketModule } from './transfermarket';

import { BidinfoModule } from './bidinfo';

import { HttpModule } from '@angular/http';
import { HttpUtilService } from './util';
import { PaginationModule } from 'ngx-bootstrap';
import { TabsModule } from 'ngx-bootstrap';
import { AlertService } from './util/alert.service';
import { AlertComponent } from './directives/alert.component';
import { AuthenticationService } from './user/shared/authentication.service';
import { AuthGuard } from './guards/auth.guard';
import { LoginComponent } from './user/login/login.component';
import { RegisterComponent } from './user/cadastrar/user-cadastrar.component';
import { UserService } from './user/shared/user.service';
import { FormsModule }   from '@angular/forms';
import { MdSelectModule, MdOptionModule, MdAutocompleteModule} from '@angular/material';
import { ToastrModule } from 'ngx-toastr';

@NgModule({
  imports:      [
    BrowserModule,
    BrowserAnimationsModule,
    MdSelectModule,
    MdOptionModule,  
    MdAutocompleteModule,  
    AppRoutingModule,
    PlayerModule,
    LeagueModule,
    TeamModule,
    TransfermarketModule,
    BidinfoModule,
    UserModule,
    HttpModule,
    PaginationModule.forRoot(),
		TabsModule.forRoot(),
    FormsModule,
    ToastrModule.forRoot(),
  
  ],

  declarations: [ 
    AppComponent,
    AlertComponent,
    LoginComponent,
    RegisterComponent   
  ],
  providers: [
		HttpUtilService,
    AuthGuard,
    AuthenticationService,
    UserService,
    AlertService,   
  ],
  bootstrap:    [ AppComponent ]
})
export class AppModule {}
