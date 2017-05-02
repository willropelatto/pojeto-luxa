/**
 * Componente de listagem de players.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Player, PlayerService } from '../shared';
import { KzPaginacaoComponent } from '../../shared';

@Component({
	selector: 'kz-player-listar',
	templateUrl: './player-listar.component.html',
	styleUrls: ['./player-listar.component.css']
})
export class PlayerListarComponent implements OnInit {

	private players: Player[];
	private idExcluir: number;
	private pagina: number;
	private totalRegistros: number;

	/**
	 * Construtor.
	 *
	 * @param PlayerService playerService
	 */
	constructor(private playerService: PlayerService,
		private route: ActivatedRoute) {
	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {
		this.totalRegistros = this.playerService.totalRegistros();
		this.pagina = +this.route.snapshot.queryParams['pagina'] || KzPaginacaoComponent.PAG_PADRAO;
		this.players = this.playerService.listarParcial(
			--this.pagina, KzPaginacaoComponent.TOTAL_PAGS_PADRAO);
	}

	/**
	 * Método responsável por armazenar o id do player a 
	 * removido.
	 *
	 * @param number id
	 */
	excluir(id: number) {
 		this.idExcluir = id;
 	}

 	/**
	 * Método responsável por remover um player.
	 */
 	onExcluir() {
 		this.playerService.excluir(this.idExcluir);
 		location.reload();
 	}

 	/**
 	 * Método responsável pela paginação.
 	 *
 	 * @param any $event Número da página atual.
 	 */
 	paginar($event: any) {
		this.pagina = $event - 1;
		this.totalRegistros = this.playerService.totalRegistros();
		this.players = this.playerService.listarParcial(
			this.pagina, KzPaginacaoComponent.TOTAL_PAGS_PADRAO);
	}
}