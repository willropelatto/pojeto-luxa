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

import {Observable} from 'rxjs/Rx';


@Component({
	selector: 'kz-transfermarket-listar',
	templateUrl: './transfermarket-listar.component.html',
	styleUrls: ['./transfermarket-listar.component.css']
})


export class TransfermarketListarComponent implements OnInit {


	ticks =20;
	private transfermarkets: Transfermarket[];
	private idExcluir: number;
	private pagina: number;
	private totalRegistros: number;
//	private players: Player[];
	private playerService : PlayerService;
	private bidinfoService : BidinfoService;
	private playerId: number;
	private bid : Bidinfo;
	

	/**
	 * Construtor.
	 *
	 * @param TransfermarketService transfermarketService
	 */
	constructor(private transfermarketService: TransfermarketService,
				_playerService: PlayerService,
				_bidinfoService: BidinfoService,
				private route: ActivatedRoute) {
	//	this.playerService = _playerService;
		this.bidinfoService = _bidinfoService;

					
	}

	getPlayers(): Player[]{
		return [];
	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {
//		this.totalRegistros = this.transfermarketService.totalRegistros();
//		this.pagina = +this.route.snapshot.queryParams['pagina'] || KzPaginacaoComponent.PAG_PADRAO;
	//	this.players = this.playerService.listarTodos();
		this.bid = new Bidinfo();
		this.transfermarkets = this.transfermarketService.listarTodos();
		let timer = Observable.timer(1000,2000);
    	timer.subscribe(t=>this.ticks = t);
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

	onBid(transferMarket: Transfermarket){
		console.log(transferMarket);
		let bidInfo = new Bidinfo();

		

		bidInfo.id = new Date().getTime();
		bidInfo.bidValue = transferMarket.bidValue;
		bidInfo.originalValue = transferMarket.originalValue;
		bidInfo.teamId = transferMarket.teamId;
		bidInfo.playerId = transferMarket.idPlayer;

		if (transferMarket.idBid == 0) { 
			this.bidinfoService.cadastrar(bidInfo)
		}else {
			bidInfo.id = transferMarket.idBid;
			this.bidinfoService.atualizar(bidInfo.id,bidInfo);
		}
	}


 	onExcluir() {
 		this.transfermarketService.excluir(this.idExcluir);
 		location.reload();
 	}

 	/**
 	 * Método responsável pela paginação.
 	 *
 	 * @param any $event Número da página atual.
 	 */
 	/*paginar($event: any) {
		this.pagina = $event - 1;
		this.totalRegistros = this.transfermarketService.totalRegistros();
		this.transfermarkets = this.transfermarketService.listarParcial(
			this.pagina, KzPaginacaoComponent.TOTAL_PAGS_PADRAO);
	}*/
}