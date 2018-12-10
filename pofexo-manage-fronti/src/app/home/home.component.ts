import { Component, OnInit } from '@angular/core';
import { Manager } from '../beans/manager';
import { ManagerService } from '../services/manager.service';
import { first } from 'rxjs/operators';
import { Team } from '../beans/team';
import { ActivatedRoute, Router } from '@angular/router';
import { TeamService } from '../services/team.service';
import { AuthenticationService } from '../services/authentication.service';

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
      private userService: ManagerService,
      private teamService: TeamService,
      private authService: AuthenticationService,
      private router: Router) {
    this.currentUser = this.authService.getCurrentUser();
    this.team = this.teamService.getCurrentTeam();

    if (this.team === null) {
      this.router.navigate(['/team']);
    }    
  }

  ngOnInit() {
    this.teamService.getTeamId(this.team.id)
    .subscribe(team => {
      this.team = team
      }
    );
  }

  private loadAllUsers() {
    this.userService.getListManager(0, 5)
      .pipe(first())
      .subscribe(users => {
        this.users = users;
      });
  }

}
