import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { Manager } from '../beans/manager';
import { Team } from '../beans/team';
import { AuthenticationService } from '../services/authentication.service';
import { MarketService } from '../services/market.service';
import { TeamService } from '../services/team.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  currentUser: Manager;
  team: Team;
  users: Manager[] = [];

  constructor(    
    private teamService: TeamService,
    private mktService: MarketService,
    private authService: AuthenticationService,
    private snackBar: MatSnackBar,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.currentUser = this.authService.getCurrentUser();
    this.team = this.teamService.getCurrentTeam();

    if (this.team === null) {
      this.router.navigate(['/team']);
    }

    this.teamService.getTeamId(this.team.id)
      .subscribe(team => {
        this.team = team
      }
      );
  }

  openMkt() {
    this.mktService.openMarket().subscribe(
      _ => {
        this.snackBar.open('Mercado Aberto.', 'OK', { duration: 5000 });
      },
      error => {
        this.snackBar.open('Não foi abrir o mercado.', 'OK', { duration: 5000 })
      }
    );
  }

}
