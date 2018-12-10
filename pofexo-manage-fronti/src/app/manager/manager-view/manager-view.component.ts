import { Component, OnInit } from '@angular/core';
import { Manager } from 'src/app/beans/manager';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { Team } from 'src/app/beans/team';
import { TeamService } from 'src/app/services/team.service';

@Component({
  selector: 'app-manager-view',
  templateUrl: './manager-view.component.html',
  styleUrls: ['./manager-view.component.css']
})
export class ManagerViewComponent implements OnInit {

  luxa: Manager;
  team: Team;

  constructor(private authService: AuthenticationService,
    private teamService: TeamService) {
    this.luxa = this.authService.getCurrentUser();
    this.team = this.teamService.getCurrentTeam();   
  }

  ngOnInit() {
  }

}
