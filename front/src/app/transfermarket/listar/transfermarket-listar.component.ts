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

import { Player } from '../../player';

import { Subscription } from 'rxjs/Subscription';

@Component({
	selector: 'kz-transfermarket-listar',
	templateUrl: './transfermarket-listar.component.html',
	styleUrls: ['./transfermarket-listar.component.css']
})
export class TransfermarketListarComponent implements OnInit {

	private players: Player[];
	private subscription: Subscription;

	private transfermarkets: Transfermarket[];
	private idExcluir: number;
	private pagina: number;
	private totalRegistros: number;

	/**
	 * Construtor.
	 *
	 * @param TransfermarketService transfermarketService
	 */
	constructor(private transfermarketService: TransfermarketService,
		private route: ActivatedRoute) {
	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {
		this.players = this.transfermarketService.getPlayers();
		this.subscription = this.transfermarketService.playersChanged
			.subscribe(
				(players: Player[]) => {
					this.players = players;
				}
			);
	}

	onEditItem(index: number){
		this.transfermarketService.startedEditing.next(index);
	}
	ngOnDestroy(){
		this.subscription.unsubscribe();
	}
		//this.totalRegistros = this.transfermarketService.totalRegistros();
		//this.pagina = +this.route.snapshot.queryParams['pagina'] || KzPaginacaoComponent.PAG_PADRAO;
		//this.transfermarkets = this.transfermarketService.listarParcial(
		//	--this.pagina, KzPaginacaoComponent.TOTAL_PAGS_PADRAO);
	//}

	/**
	 * Método responsável por armazenar o id do transfermarket a 
	 * removido.
	 *
	 * @param number id
	 */
	excluir(id: number) {
 		this.idExcluir = id;
 	}

 	/**
	 * Método responsável por remover um transfermarket.
	 */
 	onExcluir() {
 		this.transfermarketService.excluir(this.idExcluir);
 		location.reload();
 	}

 	/**
 	 * Método responsável pela paginação.
 	 *
 	 * @param any $event Número da página atual.
 	 */
 	paginar($event: any) {
		this.pagina = $event - 1;
		this.totalRegistros = this.transfermarketService.totalRegistros();
		this.transfermarkets = this.transfermarketService.listarParcial(
			this.pagina, KzPaginacaoComponent.TOTAL_PAGS_PADRAO);
	}
}