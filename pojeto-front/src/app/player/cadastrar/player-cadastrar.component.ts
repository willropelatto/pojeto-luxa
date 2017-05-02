/**
 * Componente de cadastro de players.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Router } from '@angular/router'; 

import { Player, PlayerService } from '../shared';

@Component({
	selector: 'kz-player-cadastrar',
	templateUrl: './player-cadastrar.component.html',
	styleUrls: ['./player-cadastrar.component.css']
})
export class PlayerCadastrarComponent implements OnInit {

	private player: Player;

	/**
	 * Construtor.
	 *
	 * @param Router router
	 * @param PlayerService playerService
	 */
	constructor(
		private router: Router, 
		private playerService: PlayerService) {
	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {
		this.player = new Player();
	}

	/**
	 * Método responsável por cadastrar um novo player.
	 */
	cadastrar() {
		this.player.id = new Date().getTime();
		this.playerService.cadastrar(this.player);
		this.router.navigate(['/players']);
	}
}