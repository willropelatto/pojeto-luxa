/**
 * Serviço de gerenciamento de transfermarkets.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */
 
import { Injectable } from '@angular/core';

import { Transfermarket } from './transfermarket.model';

import { Subject } from 'rxjs/Subject';

import { Player } from '../../player';

@Injectable()
export class TransfermarketService {

	playersChanged = new Subject<Player[]>();
	startedEditing = new Subject<number>();
	private players: Player[];	

	getPlayers(){
		return this.players.slice();
	}

	getPlayer(index: number){
		return this.players[index];
	}

	addPlayer(player: Player){
		this.players.push(player);
		this.playersChanged.next(this.players.slice());
	}

	addPlayers(players: Player[]){
		this.players.push(...players);
		this.playersChanged.next(this.players.slice());
	}

	deletePlayer(index: number){
		this.players.splice(index,1);
		this.playersChanged.next(this.players.slice());
	}
	updatePlayer(index: number, newPlayer: Player){
		this.players[index] = newPlayer;
		this.playersChanged.next(this.players.slice());
	}




	/**
	 * Retorna listagem de todos os transfermarkets.
	 *
	 * @return Transfermarket[] transfermarkets
	 */
	listarTodos(): Transfermarket[] {
		var transfermarkets:string = sessionStorage['transfermarkets'];
		return transfermarkets ? JSON.parse(transfermarkets) : [];
	}

	/**
	 * Cadastra um novo transfermarket.
	 *
	 * @param Transfermarket transfermarket
	 */
	cadastrar(transfermarket: Transfermarket): void {
		var transfermarkets:Transfermarket[] = this.listarTodos();
		transfermarkets.push(transfermarket);
		sessionStorage['transfermarkets'] = JSON.stringify(transfermarkets);
	}

	/**
	 * Retorna os dados de um transfermarket por id.
	 *
	 * @param number id
	 * @return Usuario transfermarket
	 */
	buscarPorId(id: number):Transfermarket {
		var transfermarkets:Transfermarket[] = this.listarTodos();
		for (let transfermarket of transfermarkets) {
			if (transfermarket.id == id) {
				return transfermarket;
			}
		}

		return new Transfermarket();
	}

	/**
	 * Atualiza os dados de um transfermarket.
	 *
	 * @param number id
	 * @param Transfermarket transfermarket
	 */
	atualizar(id: number, transfermarket: Transfermarket): void {
		var transfermarkets:Transfermarket[] = this.listarTodos();
		for (var i=0; i<transfermarkets.length; i++) {
			if (transfermarkets[i].id == id) {
				transfermarkets[i] = transfermarket;
			}
		}

		sessionStorage['transfermarkets'] = JSON.stringify(transfermarkets);
	}

	/**
	 * Remove um transfermarket.
	 *
	 * @param number id
	 */
	excluir(id: number): void {
		var transfermarkets:Transfermarket[] = this.listarTodos();
		var transfermarketsFinal:Transfermarket[] = [];

		for (let transfermarket of transfermarkets) {
			if (transfermarket.id != id) {
				transfermarketsFinal.push(transfermarket);
			}
		}

		sessionStorage['transfermarkets'] = JSON.stringify(transfermarketsFinal);
	}

	/**
	 * Retorna listagem parcial de transfermarkets.
	 *
	 * @param number pagina
	 * @param number qtdPorPagina
	 * @return Transfermarket[] transfermarkets
	 */
	listarParcial(pagina: number, qtdPorPagina: number): Transfermarket[] {
		let storage: string = sessionStorage['transfermarkets'];
		let transfermarkets: Transfermarket[] = storage ? JSON.parse(storage) : [];

		let transfermarketsParcial: Transfermarket[] = [];
		for (let i = ( pagina * qtdPorPagina ); i < (pagina * qtdPorPagina + qtdPorPagina); i++) {
			if (i >= transfermarkets.length) {
				break;
			}
			transfermarketsParcial.push(transfermarkets[i]);
		}

		return transfermarketsParcial;
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
