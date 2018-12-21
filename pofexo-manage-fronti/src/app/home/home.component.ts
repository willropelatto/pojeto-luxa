import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { interval } from 'rxjs';
import { takeWhile } from 'rxjs/operators';
import { Manager } from '../beans/manager';
import { Team } from '../beans/team';
import { AuthenticationService } from '../services/authentication.service';
import { MarketService } from '../services/market.service';
import { NotificationService } from '../services/notification.service';
import { TeamService } from '../services/team.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {

  currentUser: Manager;
  team: Team;
  users: Manager[] = [];

  private notificar: boolean;

  constructor(
    // private userService: ManagerService,
    private teamService: TeamService,
    private mktService: MarketService,
    private authService: AuthenticationService,
    private snackBar: MatSnackBar,
    private router: Router,
    private ntfService: NotificationService,

  ) {
  }

  ngOnInit() {
    this.notificar = true;
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
    this.readNotifications();  
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

  readNotifications(){
    console.log("entrou na porra.")
    let msgInterval = interval(1000);
    msgInterval.pipe(takeWhile(() => this.notificar));

    msgInterval.subscribe(() => {
       this.ntfService.getNotificationTeam(this.team.id, null, null).subscribe(x => {
          console.log(x);
       })
   });
  }

  ngOnDestroy(){
    this.notificar = false;
  }
  // private loadAllUsers() {
  //   this.userService.getListManager(0, 5)
  //     .pipe(first())
  //     .subscribe(users => {
  //       this.users = users;
  //     });
  // }

}
