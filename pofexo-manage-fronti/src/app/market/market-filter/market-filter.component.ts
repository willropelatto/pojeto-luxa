import { Component, OnInit, Input } from '@angular/core';
import { PlayerService } from 'src/app/services/player.service';
import { PlayerFilter } from 'src/app/beans/player-filter';
import { Player } from 'src/app/beans/player';
import { POSITIONS } from 'src/app/beans/misc';
import { Router, NavigationExtras, ActivatedRoute } from '@angular/router';
import { Team } from 'src/app/beans/team';

@Component({
  selector: 'app-market-filter',
  templateUrl: './market-filter.component.html',
  styleUrls: ['./market-filter.component.css']
})
export class MarketFilterComponent implements OnInit {

  @Input() filter = new PlayerFilter();
  players: Player[];
  positions = POSITIONS;
  private team: Team;

  constructor(
    private playerService: PlayerService,
    private router: Router,
    private route: ActivatedRoute    
  ) { 
    this.team = JSON.parse(localStorage.getItem('currentTeam'));
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
