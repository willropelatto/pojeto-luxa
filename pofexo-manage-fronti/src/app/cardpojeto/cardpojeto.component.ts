import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Player } from '../beans/player';
import { PLAYERATTRIBUTES } from '../beans/misc';
import { Team } from '../beans/team';
import { TeamService } from '../services/team.service';

@Component({
  selector: 'cardpojeto',
  templateUrl: './cardpojeto.component.html',
  styleUrls: ['./cardpojeto.component.css']
})
export class CardpojetoComponent implements OnInit {

  @Output() informaBid = new EventEmitter();
  team: Team;

  constructor(private teamService: TeamService) { }

  ngOnInit() {
    this.player.attributes.sort(function (a, b) {
      return a.name < b.name ? -1 : a.name > b.name ? 1 : 0;
    })

    this.team = this.teamService.getCurrentTeam();
  }

  @Input() player: Player;

  feedback(player: Player) {
    this.informaBid.emit(player);
  }

  getHeader(): string {
    return "header" + this.player.id.toString();
  }

  getCollapse(): string {
    return "collapse" + this.player.id.toString();
  }

  getCollapseId(): string {
    return "#collapse" + this.player.id.toString();
  }

  chamadaRest() {
    console.log('Chamada rest do botao DAR LANCE.');
  }

  getAttributeColor(attribute: number) {
    let res: string;
    if (attribute >= 90) {
      res = 'stats-90-99'
    } else if (attribute >= 80) {
      res = 'stats-80-89'
    } else if (attribute >= 70) {
      res = 'stats-70-79'
    } else if (attribute >= 50) {
      res = 'stats-50-69'
    } else if (attribute < 50) {
      res = 'stats-1-49'
    }
    return ' chip-attr ' + res;
  }

  getRatingColor(attribute: number) {
    let res: string;
    if (attribute >= 75) {
      res = 'rating-75-99'
    } else if (attribute >= 65) {
      res = 'rating-65-74'
    } else if (attribute < 65) {
      res = 'rating-1-64'
    }
    return ' avt-col ' + res;
  }




}
