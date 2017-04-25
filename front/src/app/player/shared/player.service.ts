/**
 * Serviço de gerenciamento de players.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */
 
import { Injectable } from '@angular/core';

import { Player } from './player.model';


@Injectable()
export class PlayerService {

	/**
	 * Retorna listagem de todos os players.
	 *
	 * @return Player[] players
	 */
	listarTodos(): Player[] {
		var players:string = sessionStorage['players'];
		return players ? JSON.parse(players) : [];
	}

	/**
	 * Cadastra um novo player.
	 *
	 * @param Player player
	 */
	cadastrar(player: Player): void {
		var players:Player[] = this.listarTodos();
		players.push(player);
		sessionStorage['players'] = JSON.stringify(players);
	}

	/**
	 * Retorna os dados de um player por id.
	 *
	 * @param number id
	 * @return Usuario player
	 */
	buscarPorId(id: number):Player {
		var players:Player[] = this.listarTodos();
		for (let player of players) {
			if (player.id == id) {
				return player;
			}
		}

		return new Player();
	}

	/**
	 * Atualiza os dados de um player.
	 *
	 * @param number id
	 * @param Player player
	 */
	atualizar(id: number, player: Player): void {
		var players:Player[] = this.listarTodos();
		for (var i=0; i<players.length; i++) {
			if (players[i].id == id) {
				players[i] = player;
			}
		}

		sessionStorage['players'] = JSON.stringify(players);
	}

	/**
	 * Remove um player.
	 *
	 * @param number id
	 */
	excluir(id: number): void {
		var players:Player[] = this.listarTodos();
		var playersFinal:Player[] = [];

		for (let player of players) {
			if (player.id != id) {
				playersFinal.push(player);
			}
		}

		sessionStorage['players'] = JSON.stringify(playersFinal);
	}

	/**
	 * Retorna listagem parcial de players.
	 *
	 * @param number pagina
	 * @param number qtdPorPagina
	 * @return Player[] players
	 */
	listarParcial(pagina: number, qtdPorPagina: number): Player[] {
		let storage: string = sessionStorage['players'];
		let players: Player[] = storage ? JSON.parse(storage) : [];

		let playersParcial: Player[] = [];
		for (let i = ( pagina * qtdPorPagina ); i < (pagina * qtdPorPagina + qtdPorPagina); i++) {
			if (i >= players.length) {
				break;
			}
			playersParcial.push(players[i]);
		}

		return playersParcial;
	}

	/**
	 * Retorna o total de pessoas.
	 *
	 * @return number total de registros
	 */
	totalRegistros(): number {
		return this.listarTodos().length;
	}
}
