import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { Team } from 'src/app/beans/team';
import { TeamService } from 'src/app/services/team.service';
import { ActivatedRoute } from '@angular/router';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { Player } from 'src/app/beans/player';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-team-view',
  templateUrl: './team-view.component.html',
  styleUrls: ['./team-view.component.css']
})
export class TeamViewComponent implements OnInit {

  displayedColumns: string[] = ['position', 'name', 'rating', 'status'];
  dataSource: MatTableDataSource<Player>;

  @Input() team: Team;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private teamService: TeamService,
    private route: ActivatedRoute
  ) { }

  getTeam(): void {
    this.team = JSON.parse(localStorage.getItem('currentTeam'));
    const id = this.team.id;

    this.teamService.getTeamId(id)
      .subscribe(team => {
        this.team = team
        this.dataSource = new MatTableDataSource<Player>(this.team.players);
        setTimeout(() => this.dataSource.paginator = this.paginator); //Isso é loucura, só funciona o paginator se colocar esse comando de timout
      }
      );
  }

  ngOnInit() {
    this.getTeam();
  }

}
