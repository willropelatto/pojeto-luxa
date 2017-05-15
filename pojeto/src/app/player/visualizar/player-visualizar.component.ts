/**
 * Componente de visualização de player.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Player, PlayerService } from '../shared';

@Component({
	selector: 'kz-player-visualizar',
	templateUrl: './player-visualizar.component.html',
	styleUrls: ['./player-visualizar.component.css']
})
export class PlayerVisualizarComponent implements OnInit {

	private id: number;
	private player: Player;

	/**
	 * Construtor.
	 *
	 * @param ActivatedRoute route
	 * @param PlayerService playerService
	 */
	constructor(
		private route: ActivatedRoute, 
		private playerService: PlayerService) {
	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {
		this.id = +this.route.snapshot.params['id'];
		this.player = this.playerService.buscarPorId(this.id);
	}
}