import { Component, OnInit, Input } from '@angular/core';
import { TeamService } from 'src/app/services/team.service';
import { Team } from 'src/app/beans/team';
import { MatSnackBar } from '@angular/material';
import { Manager } from 'src/app/beans/manager';
import { Router } from '@angular/router';

@Component({
  selector: 'app-team-create',
  templateUrl: './team-create.component.html',
  styleUrls: ['./team-create.component.css']
})
export class TeamCreateComponent implements OnInit {

  @Input() team: Team;
  user: Manager;

  constructor(
    private teamService: TeamService,
    private snackBar: MatSnackBar,
    private router: Router
  ) { }

  newTeam(tele: Team): void {
    this.teamService.addTeam(tele)
      .subscribe(team => {
        this.team = team;
        this.snackBar.open('Time registrado com sucesso.', 'OK', { duration: 5000 })
        this.router.navigate(['/team/view']);
      },
        error => {
          this.snackBar.open('Não foi possivel registrar o time', 'OK', { duration: 5000 })
        }
      );
  }

  ngOnInit() {    
    this.user = JSON.parse(localStorage.getItem('currentUser'));
    this.team = new Team();
    this.team.idUser = this.user.id;
    this.team.budget = 15000;  
  }

}
