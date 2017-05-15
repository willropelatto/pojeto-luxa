/**
 * Componente de visualização de transfermarket.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Transfermarket, TransfermarketService } from '../shared';

@Component({
	selector: 'kz-transfermarket-visualizar',
	templateUrl: './transfermarket-visualizar.component.html',
	styleUrls: ['./transfermarket-visualizar.component.css']
})
export class TransfermarketVisualizarComponent implements OnInit {

	private id: number;
	private transfermarket: Transfermarket;

	/**
	 * Construtor.
	 *
	 * @param ActivatedRoute route
	 * @param TransfermarketService transfermarketService
	 */
	constructor(
		private route: ActivatedRoute, 
		private transfermarketService: TransfermarketService) {
	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {
		this.id = +this.route.snapshot.params['id'];
		this.transfermarket = this.transfermarketService.buscarPorId(this.id);
	}
}