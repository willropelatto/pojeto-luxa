var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
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
import { CardpojetoComponent } from './cardpojeto/cardpojeto.component';
import { WsocketComponent } from './wsocket/wsocket.component';
var AppModule = /** @class */ (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        NgModule({
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
                ManagerViewComponent,
                CardpojetoComponent,
                WsocketComponent
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
    ], AppModule);
    return AppModule;
}());
export { AppModule };
//# sourceMappingURL=app.module.js.map