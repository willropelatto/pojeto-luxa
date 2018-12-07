import { Component, OnInit, ViewChild } from '@angular/core';
import { MarketService } from '../services/market.service';
import { PlayerService } from '../services/player.service';
import { Player } from '../beans/player';
import { MatPaginator, MatSnackBar } from '@angular/material';
import { tap } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';
import { PlayerFilter } from '../beans/player-filter';

@Component({
  selector: 'app-market',
  templateUrl: './market.component.html',
  styleUrls: ['./market.component.css']
})
export class MarketComponent implements OnInit {

  players: Player[];
  totalPlayer = 0;
  private playerFilter: PlayerFilter;
  private team = 0;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private marketService: MarketService,
    private playerService: PlayerService,
    private snackBar: MatSnackBar,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.route.queryParams.subscribe(params => {
      this.playerFilter = params["playerFilter"];
    });

    this.team = JSON.parse(localStorage.getItem('currentTeam'));
    if (!(this.team > 0)) {
      this.router.navigate(['/team']);
    }
  }

  placeBid(player: Player) {
    player.bid.team = this.team;
    player.bid.bidValue = player.bid.nextValue;
    console.log(player);
    this.marketService.placeBid(player).
      subscribe(pl => {
        player = pl;

        if (player.bid.status == "NO_MONEY") {
          this.snackBar.open('Dinheiro insuficiente.', 'OK', { duration: 5000 });
        } else
          if (player.bid.status == "RESYNC") {
            this.snackBar.open('Lance superado.', 'OK', { duration: 5000 });
          } else
            if (player.bid.status == "MARKET_CLOSE") {
              this.snackBar.open('Mercado está fechado.', 'OK', { duration: 5000 });
            } else {
              this.snackBar.open('Lance efetuado pelo jogador ' + pl.name + '.', 'OK', { duration: 5000 });
            }
        //todo: refresh na página?, só queria atualizar esse registro...
      },
        error => {
          this.snackBar.open('Não foi possivel dar o lance no jogador.', 'OK', { duration: 5000 })
        }
      );
  }

  ngOnInit() {
    this.getPlayers(0, 5);
  }

  ngAfterViewInit() {
    this.paginator.page
      .pipe(
        tap(() => this.loadPlayersPage())
      )
      .subscribe();
  }

  loadPlayersPage() {
    this.getPlayers(this.paginator.pageIndex,
      this.paginator.pageSize);
  }

  getPlayers(pageIndex, pageSize) {
    let promise = this.playerService.getFilteredPlayers(pageIndex, pageSize, this.playerFilter).toPromise();

    promise.then((juca) => {
      this.players = juca['content'];
      this.totalPlayer = juca['totalElements'];
    });
  }
  receiveBid(player: Player) {
    this.placeBid(player);
  }

}
