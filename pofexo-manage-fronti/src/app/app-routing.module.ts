import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PlayersComponent } from './players/players.component';
import { ManagerComponent } from './manager/manager.component';
import { TeamCreateComponent } from './team/team-create/team-create.component';
import { TeamViewComponent } from './team/team-view/team-view.component';
import { LeagueComponent } from './league/league.component';
import { MarketComponent } from './market/market.component';
import { MarketFilterComponent } from './market/market-filter/market-filter.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './guards/auth.guard';
import { TeamComponent } from './team/team.component';
import { ManagerViewComponent } from './manager/manager-view/manager-view.component';
import { TeamCreateGuard } from './guards/team-create.guard';

const routes: Routes = [
  { path: 'players', component: PlayersComponent,  canActivate: [AuthGuard]},
  { path: 'manager', component: ManagerComponent},
  { path: 'manager/view', component: ManagerViewComponent,  canActivate: [AuthGuard] },
  { path: 'team/view', component: TeamViewComponent, canActivate: [AuthGuard] },
  { path: 'team/new', component: TeamCreateComponent, canActivate: [AuthGuard, TeamCreateGuard] },
  { path: 'team', component: TeamComponent, canActivate: [AuthGuard] },
  { path: 'leagues', component: LeagueComponent, canActivate: [AuthGuard]},
  { path: 'market/panel', component: MarketComponent, canActivate: [AuthGuard]},
  { path: 'market/filter', component: MarketFilterComponent, canActivate: [AuthGuard]},
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
  { path: 'login', component: LoginComponent},
  { path: '', redirectTo: '/home', pathMatch: 'full' },
];

@NgModule({
  exports: [ 
    RouterModule
  ],   
  imports: [
    RouterModule.forRoot(routes)
  ],
  declarations: []
})
export class AppRoutingModule { }
