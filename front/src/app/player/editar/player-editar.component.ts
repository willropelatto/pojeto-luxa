/**
 * Componente de edição de player.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router'; 

import { Player, PlayerService } from '../shared';

@Component({
	selector: 'kz-player-editar',
	templateUrl: './player-editar.component.html',
	styleUrls: ['./player-editar.component.css']
})
export class PlayerEditarComponent implements OnInit {

	private id: number;
	private player: Player;
	private msgErro: string;

	/**
	 * Construtor.
	 *
	 * @param ActivatedRoute route
	 * @param Router router
	 * @param PlayerService playerService
	 */
	constructor(
		private route: ActivatedRoute, 
		private router: Router, 
		private playerService: PlayerService) {
	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {
		this.id = +this.route.snapshot.params['id'];
		this.playerService.buscarPorId(this.id);
		//		.subscribe(player => this.player = player,
		//		error => this.msgErro = error);
	}

	/**
	 * Método responsável por atualizar os dados de um player.
	 */
	atualizar() {
		this.playerService.atualizar(this.id, this.player);
		this.router.navigate(['/players']);
	}
}