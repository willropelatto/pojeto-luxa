import { Component, OnInit } from '@angular/core';
import { Player } from '../beans/player';
import { debug } from 'util';
import { PlayerFilter } from '../beans/player-filter';
import { PlayerService } from '../services/player.service';

@Component({
  selector: 'app-players',
  templateUrl: './players.component.html',
  styleUrls: ['./players.component.css']
})
export class PlayersComponent implements OnInit {

  players: Player[];
  selectedPlayer: Player;
  size: number = 5;
  page: number = 0;
  
  
  constructor(private playerService: PlayerService) { }

  onSelect(player: Player) {    
    this.selectedPlayer = player;
  }

  getPlayers(): void {    
    let xuxa = new PlayerFilter();  
    xuxa.name = 'ronaldo';    
    this.playerService.getFilteredPlayers(0,5,xuxa).subscribe(
      juca => this.players = juca['content']
    );
    
//    this.playerService.getPlayers(0,10).subscribe(juca => 
//      this.players = juca['content']
//    );    
  }

  getPlayer(id: number): void {    
    this.playerService.getPlayer(id).subscribe(/*juca => 
      this.cheese = juca*/
    );    
  }  

/*
carregarLivros() {
this._livroService.carregarLivros(this.currentPage, this.pageSize)
.subscribe(data => {
this.filteredData = data['content'];
this.data = data['content'];
this.filteredTotal = data['totalElements'];
});
}

*/



  ngOnInit() {
    this.getPlayers();    
  }

}
