import { Component, OnInit, Input } from '@angular/core';
import { Player } from '../beans/player';

@Component({
  selector: 'app-player-detail',
  templateUrl: './player-detail.component.html',
  styleUrls: ['./player-detail.component.css']
})
export class PlayerDetailComponent implements OnInit {

  @Input() player: Player;

  constructor() { }

  ngOnInit() {
  }

}
