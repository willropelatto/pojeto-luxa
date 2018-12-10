import { Component, OnInit } from '@angular/core';
import { Manager } from '../beans/manager';
import { Team } from '../beans/team';
import { TeamService } from '../services/team.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.css']
})
export class TeamComponent implements OnInit {

  team: Team;
  private user: Manager;
  private teamId = 0;
  private return: string;

  constructor(
    private teamService: TeamService,
    private router: Router
  ) { }

  ngOnInit() {
    this.team = JSON.parse(localStorage.getItem('currentTeam'));
    this.user = JSON.parse(localStorage.getItem('currentUser'));   
    if (this.team !== null) {
      this.redirectToView();      
    } else {
      this.getTeam();
    }

  }

  private redirectToView() {
    this.router.navigate(['/team/view']);
  }

  private getTeam() {
    this.teamService.getTeamUser(this.user.id)
      .subscribe(tm => {
        if (tm) {
          this.teamId = tm.id;          
          localStorage.setItem('currentTeam', JSON.stringify(tm));          
          this.redirectToView();                    
        } else {          
          this.router.navigate(['/team/new']);        
        }
      });
  }

}
