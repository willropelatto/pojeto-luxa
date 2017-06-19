import { AlertService } from './../../util/alert.service';
/**
 * Componente de listagem de transfermarkets.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */


import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Transfermarket, TransfermarketService } from '../shared';
import { KzPaginacaoComponent } from '../../shared';

import { Player, PlayerService } from '../../player';

import { Bidinfo, BidinfoService } from '../../bidinfo';

import { Inject } from '@angular/core';

import { Observable } from 'rxjs/Rx';


@Component({
	selector: 'kz-transfermarket-listar',
	templateUrl: './transfermarket-listar.component.html',
	styleUrls: ['./transfermarket-listar.component.css']
})


export class TransfermarketListarComponent implements OnInit {


	ticks = 20;
	public transfermarkets: Transfermarket[];
	public idExcluir: number;
	public pagina: number;
	public totalRegistros: number;
	//	public players: Player[];
	public playerService: PlayerService;
	public bidinfoService: BidinfoService;
	public playerId: number;
	public bid: Bidinfo;


	/**
	 * Construtor.
	 *
	 * @param TransfermarketService transfermarketService
	 */
	constructor(public transfermarketService: TransfermarketService,
		public alertService: AlertService,
		_playerService: PlayerService,
		_bidinfoService: BidinfoService,
		public route: ActivatedRoute) {
		this.bidinfoService = _bidinfoService;


	}

	getPlayers(): Player[] {
		return [];
	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {
		this.bid = new Bidinfo();
		this.transfermarkets = this.transfermarketService.listarTodos();
		let timer = Observable.timer(1000, 2000);
		timer.subscribe(t => this.ticks = t);
	}

	/**
	 * Método responsável por armazenar o id do transfermarket a 
	 * removido.
	 *
	 * @param number id
	 */
	excluir(id: number) {
		this.idExcluir = id;
	}

	onBid(transferMarket: Transfermarket) {
		let bidInfo = new Bidinfo();
		bidInfo.bidValue = transferMarket.bidValue;
		bidInfo.originalValue = transferMarket.originalValue;
		bidInfo.teamID = transferMarket.teamId;
		bidInfo.playerID = transferMarket.idPlayer;
		console.log(bidInfo);
		if (transferMarket.bidValue  === this.transfermarketService.bid(transferMarket.rating)) {
			this.bidinfoService.initialBid(bidInfo)
				.subscribe(
				(res) => {
					this.alertService.success('Lance efetuado com sucesso!', true);
				},
				(err) => {
					this.alertService.error(err);
				});
		} else {
			this.bidinfoService.placeBid(bidInfo)
				.subscribe(
				(res) => {
					this.alertService.success('Lance efetuado com sucesso!', true);
				},
				(err) => {
					this.alertService.error(err);
			});
		}
	}


	onExcluir() {
		this.transfermarketService.excluir(this.idExcluir);
		location.reload();
	}

}