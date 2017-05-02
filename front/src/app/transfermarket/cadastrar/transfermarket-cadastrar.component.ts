/**
 * Componente de cadastro de transfermarkets.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Router } from '@angular/router'; 

import { Transfermarket, TransfermarketService } from '../shared';

@Component({
	selector: 'kz-transfermarket-cadastrar',
	templateUrl: './transfermarket-cadastrar.component.html',
	styleUrls: ['./transfermarket-cadastrar.component.css']
})
export class TransfermarketCadastrarComponent implements OnInit {

	private transfermarket: Transfermarket;

	/**
	 * Construtor.
	 *
	 * @param Router router
	 * @param TransfermarketService transfermarketService
	 */
	constructor(
		private router: Router, 
		private transfermarketService: TransfermarketService) {
	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {
		this.transfermarket = new Transfermarket();
	}

	/**
	 * Método responsável por cadastrar um novo transfermarket.
	 */
	cadastrar() {
		//this.transfermarket.id = new Date().getTime();
		this.transfermarketService.cadastrar(this.transfermarket);
		this.router.navigate(['/transfermarkets']);
	}
}