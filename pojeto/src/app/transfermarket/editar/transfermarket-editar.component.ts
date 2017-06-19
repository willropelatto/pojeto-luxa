/**
 * Componente de edição de transfermarket.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router'; 

import { Transfermarket, TransfermarketService } from '../shared';

@Component({
	selector: 'kz-transfermarket-editar',
	templateUrl: './transfermarket-editar.component.html',
	styleUrls: ['./transfermarket-editar.component.css']
})
export class TransfermarketEditarComponent implements OnInit {

	public id: number;
	public transfermarket: Transfermarket;

	/**
	 * Construtor.
	 *
	 * @param ActivatedRoute route
	 * @param Router router
	 * @param TransfermarketService transfermarketService
	 */
	constructor(
		public route: ActivatedRoute, 
		public router: Router, 
		public transfermarketService: TransfermarketService) {
	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {
		this.id = +this.route.snapshot.params['id'];
		this.transfermarket = this.transfermarketService.buscarPorId(this.id);
	}

	/**
	 * Método responsável por atualizar os dados de um transfermarket.
	 */
	atualizar() {
		this.transfermarketService.atualizar(this.id, this.transfermarket);
		this.router.navigate(['/transfermarkets']);
	}
}