import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { Team } from 'src/app/beans/team';
import { TeamService } from 'src/app/services/team.service';
import { ActivatedRoute } from '@angular/router';
import { MatPaginator, MatTableDataSource, MatSnackBar } from '@angular/material';
import { Player } from 'src/app/beans/player';
import { tap } from 'rxjs/operators';
import { MarketService } from 'src/app/services/market.service';

@Component({
  selector: 'app-team-view',
  templateUrl: './team-view.component.html',
  styleUrls: ['./team-view.component.css']
})
export class TeamViewComponent implements OnInit {

  displayedColumns: string[] = ['position', 'name', 'rating', 'status', 'action'];
  dataSource: MatTableDataSource<Player>;

  @Input() team: Team;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private teamService: TeamService,
    private marketService: MarketService,
    private snackBar: MatSnackBar,
    private route: ActivatedRoute
  ) { }

  getTeam(): void {
    this.team = this.teamService.getCurrentTeam();
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

  sellPlayer(player: Player) {
    player.bid.team = this.team.id;
    player.bid.bidValue = player.bid.nextValue;
    console.log(player);
    this.marketService.dismissPlayer(player).
      subscribe(p => {
        this.teamService.getTeamId(this.team.id).subscribe();
        this.snackBar.open(p.name + ' dispensado.', 'OK', { duration: 5000 })
      },
        error => {
          this.snackBar.open('Não foi possivel dispensar o jogador.', 'OK', { duration: 5000 })
        }
      );
  }

}
