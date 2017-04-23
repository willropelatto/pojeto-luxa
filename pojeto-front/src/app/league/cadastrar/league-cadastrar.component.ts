/**
 * Componente de cadastro de leagues.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Router } from '@angular/router'; 

import { League, LeagueService } from '../shared';

@Component({
	selector: 'kz-league-cadastrar',
	templateUrl: './league-cadastrar.component.html',
	styleUrls: ['./league-cadastrar.component.css']
})
export class LeagueCadastrarComponent implements OnInit {

	private league: League;

	/**
	 * Construtor.
	 *
	 * @param Router router
	 * @param LeagueService leagueService
	 */
	constructor(
		private router: Router, 
		private leagueService: LeagueService) {
	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {
		this.league = new League();
	}

	/**
	 * Método responsável por cadastrar um novo league.
	 */
	cadastrar() {
		this.league.id = new Date().getTime();
		this.leagueService.cadastrar(this.league);
		this.router.navigate(['/leagues']);
	}
}