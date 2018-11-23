import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { AppComponent } from './app.component';
import { PlayersComponent } from './players/players.component';
import { PlayerDetailComponent } from './player-detail/player-detail.component';
import { AppRoutingModule } from './/app-routing.module';
import { ManagerComponent } from './manager/manager.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DemoMaterialModule } from './material-modules';
import { TeamViewComponent } from './team/team-view/team-view.component';
import { TeamCreateComponent } from './team/team-create/team-create.component';
import { LeagueComponent } from './league/league.component';
import { MarketComponent } from './market/market.component';
import { MarketFilterComponent } from './market/market-filter/market-filter.component';
import { AlertComponent } from './alert/alert.component';
import { ErrorInterceptor } from './misc/error.interceptor';
import { JwtInterceptor } from './misc/jwt.interceptor';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { TeamComponent } from './team/team.component';
import { ManagerViewComponent } from './manager/manager-view/manager-view.component';

@NgModule({
  declarations: [
    AppComponent,
    PlayersComponent,
    PlayerDetailComponent,
    ManagerComponent,
    DashboardComponent,
    TeamViewComponent,
    TeamCreateComponent,
    LeagueComponent,
    MarketComponent,
    MarketFilterComponent,
    AlertComponent,
    LoginComponent,
    HomeComponent,
    TeamComponent,
    ManagerViewComponent
    
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    DemoMaterialModule,    
    ReactiveFormsModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
