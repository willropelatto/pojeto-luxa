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
	public players:Array<string> = [];
	private value:any = {};


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
				let listPlayers: Array<string> = [];
				for (let list of players){
					listPlayers.push(list.name);
					
				}
				this.players = listPlayers;											
			});
	}

	/**
	 * Método responsável por cadastrar um novo transfermarket.
	 */
	cadastrar() {
		//this.transfermarket.id = new Date().getTime();
		this.transfermarketService.cadastrar(this.transfermarket);
		this.router.navigate(['/transfermarkets']);
	}

	public selected(value: any): void {
		console.log('Selected value is: ', value);
	}

	public removed(value: any): void {
		console.log('Removed value is: ', value);
	}

	public typed(value: any): void {
		console.log('New search input: ', value);
	}

	public refreshValue(value: any): void {
		this.value = value;
	}

	
}