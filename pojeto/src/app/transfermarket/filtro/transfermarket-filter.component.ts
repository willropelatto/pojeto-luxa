import { TransfermarketNameFilter } from './../shared/transfermarket.pipe';

import { Team } from './../../team/shared/team.model';
import { PlayerFilter } from './../shared/playerFilter.model';
import { AlertService } from './../../util/alert.service';
/**
 * Componente de listagem de transfermarkets.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */


import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Transfermarket, TransfermarketService } from '../shared';
import { KzPaginacaoComponent } from '../../shared';

import { Player, PlayerService } from '../../player';

import { Bidinfo, BidinfoService } from '../../bidinfo';

import { Inject } from '@angular/core';

import { Observable } from 'rxjs/Rx';

import { AnonymousSubscription } from "rxjs/Subscription";

import { PaginationInstance} from 'ngx-pagination';



@Component({
	selector: 'pojeto-transfermarket-filter',	
	templateUrl: './transfermarket-filter.component.html',
	styleUrls: ['./transfermarket-filter.component.css'],
	
})


export class TransfermarketFilterComponent implements OnInit {

	private timerSubscription: AnonymousSubscription;
	private playersSubscription: AnonymousSubscription;


	ticks = 20;
	private transfermarkets: Transfermarket[];
	private idExcluir: number;
	private pagina: number;
	private totalRegistros: number;
	private playerService: PlayerService;
	private bidinfoService: BidinfoService;
	private playerId: number;
	private bid: Bidinfo;
	private playerFilter: PlayerFilter;
	private team: Team;


	constructor(private transfermarketService: TransfermarketService,
		private alertService: AlertService,
		_playerService: PlayerService,
		_bidinfoService: BidinfoService,
		private route: ActivatedRoute) {
		this.bidinfoService = _bidinfoService;

		this.route.queryParams.subscribe(params => {
			this.playerFilter = params["playerFilter"];
			console.log(this.playerFilter);
		});

	}

	public ngOnDestroy(): void {
		if (this.playersSubscription) {
			this.playersSubscription.unsubscribe();
		}
		if (this.timerSubscription) {
			this.timerSubscription.unsubscribe();
		}
	}



	getPlayers(): Player[] {
		return [];
	}


	ngOnInit() {
		this.bid = new Bidinfo();
		//this.transfermarkets = this.transfermarketService.listarFilter(this.playerFilter);
		this.refreshData();
		this.team = this.transfermarketService.getTeam();
		let timer = Observable.timer();
		timer.subscribe();


	}
	public filter: string = '';
	public nameFilter: string;
	public positionFilter : string;
	public ratingFilter : number;
	public bidValueFilter: number;
	public originalValueFilter: number;
	public hasBidFilter: boolean;
	public bidAprovedFilter: boolean;

	public config: PaginationInstance = {
        id: 'advanced',
        itemsPerPage: 5,	
        currentPage: 1
    };

	public labels: any = {
        previousLabel: 'Anterior',
        nextLabel: 'Próximo',
        screenReaderPaginationLabel: 'Paginação',
        screenReaderPageLabel: 'pagina',
        screenReaderCurrentLabel: `Você está na página`
    };

	onPageChange(number: number) {
        console.log('change to page', number);
        this.config.currentPage = number;
    }

	private subscribeToData(): void {

		this.timerSubscription = Observable.timer(60000)
			.subscribe(() => this.refreshData());
	}

	private refreshData(): void {
		this.playersSubscription = this.transfermarketService.listarFilterObservable(this.playerFilter).
			subscribe(
				(data: Transfermarket[]) => {
					this.transfermarkets = data;
					this.team = this.transfermarketService.getTeam();
					this.subscribeToData();
				},
				function (error) {
					console.log(error);
				},
				function () {
					console.log('complete');
				}
			);

	}
	onRefresh() {
		this.bid = new Bidinfo();
		this.transfermarkets = this.transfermarketService.listarFilter(this.playerFilter);
	}

	check(transferMarket: Transfermarket): boolean {
		let mensagem: string = '';
		if (transferMarket.bidAproved) {
			mensagem = 'Você já está vencendo este leilão!';
		} else if (transferMarket.bidValue > transferMarket.team.budget) {
			mensagem = 'Você não possuí dinheiro suficiente. Lance: R$ ' + transferMarket.bidValue + ',00' +
				'. Em conta: R$' + transferMarket.team.budget + ',00';
		}

		if (mensagem.length > 0) {
			this.alertService.error(mensagem);
		}
		return mensagem.length === 0;

	}


	onBid(transferMarket: Transfermarket) {
		let bidInfo = new Bidinfo();


		bidInfo.bidValue = transferMarket.bidValue;
		bidInfo.originalValue = transferMarket.originalValue;
		bidInfo.teamID = transferMarket.teamId;
		bidInfo.playerID = transferMarket.idPlayer;

		if (this.check(transferMarket)) {
			if (transferMarket.bidValue === this.transfermarketService.bid(transferMarket.rating)) {
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

	closeMarket() {
		this.bidinfoService.closeMarket()
			.subscribe(
			(res) => {
				this.alertService.success('Mercado fechado com sucesso!', true);
			},
			(err) => {
				this.alertService.error(err);
			});
	}




}