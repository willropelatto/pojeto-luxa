import { Component, OnInit, Input } from '@angular/core';
import { PlayerService } from 'src/app/services/player.service';
import { PlayerFilter } from 'src/app/beans/player-filter';
import { Player } from 'src/app/beans/player';
import { POSITIONS } from 'src/app/beans/misc';
import { Router, NavigationExtras, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-market-filter',
  templateUrl: './market-filter.component.html',
  styleUrls: ['./market-filter.component.css']
})
export class MarketFilterComponent implements OnInit {

  @Input() filter = new PlayerFilter();
  players: Player[];
  positions = POSITIONS;
  private team = 0;

  constructor(
    private playerService: PlayerService,
    private router: Router,
    private route: ActivatedRoute    
  ) { 

    this.team = JSON.parse(localStorage.getItem('currentTeam'));
    // if (!(this.team > 0)) {
    //   console.log(this.team);
    //   console.log(this.route.snapshot.queryParams['returnUrl'] || '/');
    //   this.router.navigate(['/team']);      
    // }

  }

  ngOnInit() {
  }

  filterExtra(filterPlayer: PlayerFilter): NavigationExtras {
    let navextras: NavigationExtras = {
      queryParams: { "playerFilter": JSON.stringify(filterPlayer) }
    };
    return navextras;
  }    

  doFilterPlayer() {
    this.router.navigate(['/market/panel'], this.filterExtra(this.filter));
  }

  getPlayers(): void {
    this.playerService.getFilteredPlayers(0, 5, this.filter).subscribe(
      juca => this.players = juca['content']
    );
  }
  
}
