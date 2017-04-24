/**
 * Componente de visualização de league.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { League, LeagueService } from '../shared';

@Component({
	selector: 'kz-league-visualizar',
	templateUrl: './league-visualizar.component.html',
	styleUrls: ['./league-visualizar.component.css']
})
export class LeagueVisualizarComponent implements OnInit {

	private id: number;
	private league: League;

	/**
	 * Construtor.
	 *
	 * @param ActivatedRoute route
	 * @param LeagueService leagueService
	 */
	constructor(
		private route: ActivatedRoute, 
		private leagueService: LeagueService) {
	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {
		this.id = +this.route.snapshot.params['id'];
		this.league = this.leagueService.buscarPorId(this.id);
	}
}