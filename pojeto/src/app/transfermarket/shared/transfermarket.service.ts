
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

import { Team } from './../../team/shared/team.model';
import { User } from './../../user/shared/user.model';
import { UserService } from './../../user/shared/user.service';
import { TeamService } from './../../team/shared/team.service';

@Injectable()
export class TransfermarketService {


	private players: Player[];
	private msgErro:string;
	private bidinfoService : BidinfoService;
	private playerService : PlayerService;
	private teamService : TeamService;
	private user : User;
	private team: Team;
	private bidInfo : Bidinfo;

	
	constructor(_bidinfoService : BidinfoService,
				_playerService: PlayerService,
				_teamService : TeamService){
	  this.bidinfoService = _bidinfoService;
	  this.playerService = _playerService;
	  this.teamService = _teamService;
	  this.user = JSON.parse(localStorage.getItem('currentUser'));
	  this.playerService.listarTodos()
			.subscribe(players => this.players = players,
						error => this.msgErro = error);
						
						
	}

	/**
	 * Retorna listagem de todos os transfermarkets.
	 *
	 * @return Transfermarket[] transfermarkets
	 */


	bid(rating: number): number{

		let bidvalue = 0;

		if (rating >= 90){
			bidvalue = 5000;
		}else if (rating >= 86){
			bidvalue = 2500;
		}else if (rating >= 81){
			bidvalue = 1500;
		}else if (rating >= 76){
			bidvalue = 600;
		}else if (rating >= 71){
			bidvalue = 500;
		}else if (rating >= 61){
			bidvalue = 300;
		}else {
			bidvalue = 200;
		}


		return bidvalue;
	}



	listarTodos(): Transfermarket[] {
		let playerList : Player[]  = [];
		playerList = this.players;
		let shops : Transfermarket[] = [];
		

		this.teamService.buscarPorIdUser(this.user.id)
	  		.subscribe(team => this.team = team,
			  		   error => this.msgErro = error);

		for (let player of playerList) {

			
			let shop = new Transfermarket();
			shop.name = player.name;
			shop.position = player.position;
			shop.rating = player.rating;
			shop.idPlayer = player.id;
			console.log(player.id);
			this.bidinfoService.buscarPorIdPlayers(player.id)
				.subscribe(bidInfo => this.bidInfo = bidInfo,
						error => this.msgErro = error);

			
			let bidInfos: Bidinfo = this.bidInfo;
			console.log(bidInfos);
			console.log('^^');
			//bidInfo = this.bidinfoService.buscarPorIdPlayer(player.id);
			if (bidInfos){
				shop.idBid = bidInfos.id;
				shop.originalValue = bidInfos.originalValue;
				shop.bidValue = bidInfos.bidValue + (bidInfos.originalValue * 0.05);
				shop.teamId = bidInfos.teamID;
				console.log(bidInfos);
			}else{
				shop.idBid = 0;
				shop.bidValue =  this.bid(player.rating);
				shop.originalValue = this.bid(player.rating);
				shop.teamId = this.team.id;
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
