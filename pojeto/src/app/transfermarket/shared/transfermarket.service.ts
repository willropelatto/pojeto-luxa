import { PlayerFilter } from './playerFilter.model';
import { Observable } from 'rxjs';
import { HttpUtilService } from './../../util/http-util-service';
import { Http } from '@angular/http';

/**
 * Serviço de gerenciamento de transfermarkets.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Injectable } from '@angular/core';

import { Transfermarket } from './transfermarket.model';

import { Player, PlayerService } from '../../player';

import { Bidinfo, BidinfoService } from '../../bidinfo';

import { Team } from './../../team/shared/team.model';
import { User } from './../../user/shared/user.model';
import { UserService } from './../../user/shared/user.service';
import { TeamService } from './../../team/shared/team.service';

@Injectable()
export class TransfermarketService {

	let
	private players: Array<Player>;
	private msgErro: string;
	private bidinfoService: BidinfoService;
	private playerService: PlayerService;
	private teamService: TeamService;
	private user: User;
	private team: Team;
	private bidInfo: Bidinfo;


	constructor(_bidinfoService: BidinfoService,
		_playerService: PlayerService,
		_teamService: TeamService,
		private http: Http,
		private httpUtil: HttpUtilService) {
		this.bidinfoService = _bidinfoService;
		this.playerService = _playerService;
		this.teamService = _teamService;
		this.user = JSON.parse(localStorage.getItem('currentUser'));



	}

	/**
	 * Retorna listagem de todos os transfermarkets.
	 *
	 * @return Transfermarket[] transfermarkets
	 */


	bid(rating: number): number {

		let bidvalue = 0;

		if (rating >= 90) {
			bidvalue = 5000;
		} else if (rating >= 86) {
			bidvalue = 2500;
		} else if (rating >= 81) {
			bidvalue = 1500;
		} else if (rating >= 76) {
			bidvalue = 600;
		} else if (rating >= 71) {
			bidvalue = 500;
		} else if (rating >= 61) {
			bidvalue = 300;
		} else {
			bidvalue = 200;
		}


		return bidvalue;
	}



	listarTodos(): Transfermarket[] {
		let playerList: Player[] = [];
		let shops: Transfermarket[] = [];


		this.playerService.listarTodos()
			.subscribe((players) => {
				this.players = players

				this.teamService.buscarPorIdUser(this.user.id)
					.subscribe(team => this.team = team,
					error => this.msgErro = error);

				for (let player of this.players) {

					let shop = new Transfermarket();
					shop.name = player.name;
					shop.position = player.position;
					shop.rating = player.rating;
					shop.idPlayer = player.id;
					console.log(player.id);

					this.bidinfoService.buscarPorIdPlayers(player.id)
						.subscribe((bidInfo) => {
							this.bidInfo = bidInfo;
							let bid: Bidinfo = this.bidInfo;
							console.log(this.bidInfo);
							if (this.bidInfo) {
								shop.idBid = bid.id;
								shop.originalValue = bid.originalValue;
								shop.bidValue = bid.bidValue + (bid.originalValue * 0.05);
								shop.teamId = bid.teamID;
							} else {
								shop.idBid = 0;
								shop.bidValue = this.bid(player.rating);
								shop.originalValue = this.bid(player.rating);
								shop.teamId = this.team.id;
							}

							shops.push(shop);
						});
				}
			},

			error => this.msgErro = error);

		return shops;

	}


	listarFilter(playerFilter: PlayerFilter): Transfermarket[] {
		let playerList: Player[] = [];
		let shops: Transfermarket[] = [];


		this.playerService.listarFiltro(playerFilter)
			.subscribe((players) => {
				this.players = players

				this.teamService.buscarPorIdUser(this.user.id)
					.subscribe((team) => {
						this.team = team		
										

						for (let player of this.players) {

							let shop = new Transfermarket();
							shop.name = player.name;
							shop.position = player.position;
							shop.rating = player.rating;
							shop.idPlayer = player.id;
							console.log(player.id);
							shop.team = this.team;
							

							this.bidinfoService.buscarPorIdPlayers(player.id)
								.subscribe((bidInfo) => {
									this.bidInfo = bidInfo;
									let bid: Bidinfo = this.bidInfo;
									console.log(this.bidInfo);
									if (this.bidInfo) {
										shop.idBid = bid.id;
										shop.originalValue = bid.originalValue;
										shop.bidValue = bid.bidValue + (bid.originalValue * 0.05);
										shop.teamId = bid.teamID;
										shop.hasBid = true;
										shop.bidAproved = (bid.teamID === this.team.id);
									} else {
										shop.idBid = 0;
										shop.bidValue = this.bid(player.rating);
										shop.originalValue = this.bid(player.rating);
										shop.teamId = this.team.id;
										shop.hasBid = false;
										shop.bidAproved = false;
									}

									shops.push(shop);
								});
						}
					}, error => this.msgErro = error);


			},

			error => this.msgErro = error);

		return shops;

	}

	getTeam(){
		return this.team;
	}



	/**
	 * Cadastra um novo transfermarket.
	 *
	 * @param Transfermarket transfermarket
	 */
	cadastrar(transfermarket: Transfermarket): void {
		var transfermarkets: Transfermarket[] = this.listarTodos();
		transfermarkets.push(transfermarket);
		sessionStorage['transfermarkets'] = JSON.stringify(transfermarkets);
	}

	/**
	 * Retorna os dados de um transfermarket por id.
	 *
	 * @param number id
	 * @return Usuario transfermarket
	 */
	buscarPorId(id: number): Transfermarket {
		var transfermarkets: Transfermarket[] = this.listarTodos();
		for (let transfermarket of transfermarkets) {
			if (transfermarket.idPlayer == id) {
				return transfermarket;
			}
		}

		return new Transfermarket();
	}


	buscarPorPlayerId(id: number): Observable<Bidinfo> {
		let bidinfoPath = 'market/getBidFromPlayerId';
		return Observable.create(observer => {
			this.http.get(this.httpUtil.url(bidinfoPath + '/' + id),
				this.httpUtil.headers())
				.map(this.httpUtil.extrairDados)
				.catch(this.httpUtil.processarErros)
				.subscribe((data) => {
					console.log(data);
					this.bidInfo = data
					observer.next(this.bidInfo);
					observer.complete();
				});
		});
	}


	/**
	 * Atualiza os dados de um transfermarket.
	 *
	 * @param number id
	 * @param Transfermarket transfermarket
	 */
	atualizar(id: number, transfermarket: Transfermarket): void {
		var transfermarkets: Transfermarket[] = this.listarTodos();
		for (var i = 0; i < transfermarkets.length; i++) {
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
		var transfermarkets: Transfermarket[] = this.listarTodos();
		var transfermarketsFinal: Transfermarket[] = [];

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
		for (let i = (pagina * qtdPorPagina); i < (pagina * qtdPorPagina + qtdPorPagina); i++) {
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
