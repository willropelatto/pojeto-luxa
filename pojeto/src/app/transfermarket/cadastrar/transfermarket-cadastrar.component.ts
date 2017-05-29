import { PlayerService } from './../../player/shared/player.service';
import { Player } from './../../player/shared/player.model';
/**
 * Componente de cadastro de transfermarkets.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Component, ViewChild } from '@angular/core';
import { OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { SelectComponent} from 'ng2-select';

import { Transfermarket, TransfermarketService } from '../shared';

@Component({
	selector: 'kz-transfermarket-cadastrar',
	templateUrl: './transfermarket-cadastrar.component.html',
	styleUrls: ['./transfermarket-cadastrar.component.css']
})
export class TransfermarketCadastrarComponent implements OnInit {

	private transfermarket: Transfermarket;
	public players:Array<Player> = [];
	private value:any = {};
	selectedValue: string;

	foods = [
    {value: 'steak-0', viewValue: 'Steak'},
    {value: 'pizza-1', viewValue: 'Pizza'},
    {value: 'tacos-2', viewValue: 'Tacos'}
  ];


	/**
	 * Construtor.
	 *
	 * @param Router router
	 * @param TransfermarketService transfermarketService
	 */
	constructor(
		private router: Router,
		private transfermarketService: TransfermarketService,
		private playerService: PlayerService) {
	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {
		this.transfermarket = new Transfermarket();
		this.playerService.listarTodos()
			.subscribe((players) => {				
				this.players = players;											
			});
	}

	//https://plnkr.co/edit/?p=preview

	/**
	 * Método responsável por cadastrar um novo transfermarket.
	 */
	cadastrar() {
		//this.transfermarket.id = new Date().getTime();
		this.transfermarketService.cadastrar(this.transfermarket);
		this.router.navigate(['/transfermarkets']);
	}


	
}