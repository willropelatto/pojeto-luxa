/**
 * Serviço de gerenciamento de transfermarkets.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */
 
import { Injectable } from '@angular/core';

import { Transfermarket } from './transfermarket.model';

import { Player, PlayerService } from '../../player';

import { Bidinfo, BidinfoService} from '../../bidinfo';

@Injectable()
export class TransfermarketService {

	private bidinfoService : BidinfoService;
	private playerService : PlayerService;
	constructor(_bidinfoService : BidinfoService,
				_playerService: PlayerService){
	  this.bidinfoService = _bidinfoService;
	  this.playerService = _playerService;
	}

	/**
	 * Retorna listagem de todos os transfermarkets.
	 *
	 * @return Transfermarket[] transfermarkets
	 */

	bidValue(rating: number): number{
		let bidvalue : number;
		if (rating >= 90){
			bidvalue = 5000;			
		} else if (rating >= 86){
			bidvalue = 2500;
		} else if (rating >= 81 ){
			bidvalue = 1500;
		} else if (rating >= 76 ){
			bidvalue = 600;
		} else if(rating >= 71){
			bidvalue = 500;
		} else if (rating >= 61){
			bidvalue = 300;
		} else {
			bidvalue = 200;
		}

		return bidvalue;
	}


	listarTodos(): Transfermarket[] {

		let players : Player[] = this.playerService.listarTodos(); 
		let shops : Transfermarket[] = [];

		for (let player of players) {
			let bidInfo: Bidinfo = new Bidinfo();
			let shop = new Transfermarket();
			shop.name = player.name;
			shop.position = player.position;
			shop.rating = player.rating;
			shop.idPlayer = player.id;

			bidInfo = this.bidinfoService.buscarPorIdPlayer(player.id);
			console.log(bidInfo);
			if (bidInfo){
				//console.log(bidInfo);
				shop.idBid = bidInfo.id;
				shop.originalValue = bidInfo.originalValue;
				shop.bidAproved = bidInfo.bidAproved;
				shop.teamId = bidInfo.teamId;
			}else{
				shop.idBid = 0;
				shop.bidValue =  this.bidValue(player.rating);
				shop.originalValue = this.bidValue(player.rating);
				shop.bidAproved = true;
				shop.teamId = 1;
			}
			shops.push(shop);
		}

		return shops;
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
			if (transfermarket.idPlayer == id) {
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
			if (transfermarkets[i].idPlayer == id) {
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
			if (transfermarket.idPlayer != id) {
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
