import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Player } from '../beans/player';

@Component({
  selector: 'cardpojeto',
  templateUrl: './cardpojeto.component.html',
  styleUrls: ['./cardpojeto.component.css']
})
export class CardpojetoComponent implements OnInit {

  @Output() informaBid = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  @Input() player: Player;

  feedback(player: Player) {
    this.informaBid.emit(player);
  }

  getHeader(): string {
    return "header"+ this.player.id.toString();
  }

  getCollapse(): string {
    return "collapse"+ this.player.id.toString();
  }

  getCollapseId(): string {
    return "#collapse"+ this.player.id.toString();
  }

  chamadaRest() {
    console.log('Chamada rest do botao DAR LANCE.');
  }

}
