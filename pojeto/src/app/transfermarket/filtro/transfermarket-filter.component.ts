import { PlayerFilter } from './../shared/playerFilter.model';
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
	selector: 'pojeto-transfermarket-filter',
	templateUrl: './transfermarket-filter.component.html',
	styleUrls: ['./transfermarket-filter.component.css']
})


export class TransfermarketFilterComponent implements OnInit {


	ticks = 20;
	private transfermarkets: Transfermarket[];
	private idExcluir: number;
	private pagina: number;
	private totalRegistros: number;
	private playerService: PlayerService;
	private bidinfoService: BidinfoService;
	private playerId: number;
	private bid: Bidinfo;
	private playerFilter : PlayerFilter;


	constructor(private transfermarketService: TransfermarketService,
		private alertService: AlertService,
		_playerService: PlayerService,
		_bidinfoService: BidinfoService,
		private route: ActivatedRoute) {
		this.bidinfoService = _bidinfoService;


	}

	getPlayers(): Player[] {
		return [];
	}


	ngOnInit() {
		this.bid = new Bidinfo();
		this.playerFilter = +this.route.snapshot.params['playerFilter'];
		console.log(this.playerFilter);
		this.transfermarkets = this.transfermarketService.listarFilter(this.playerFilter);
		let timer = Observable.timer(1000, 2000);
		timer.subscribe(t => this.ticks = t);

		
	}

	

	onBid(transferMarket: Transfermarket) {
		let bidInfo = new Bidinfo();


		bidInfo.bidValue = transferMarket.bidValue;
		bidInfo.originalValue = transferMarket.originalValue;
		bidInfo.teamID = transferMarket.teamId;
		bidInfo.playerID = transferMarket.idPlayer;
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


}