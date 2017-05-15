/**
 * Componente de edição de league.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router'; 

import { League, LeagueService } from '../shared';

@Component({
	selector: 'kz-league-editar',
	templateUrl: './league-editar.component.html',
	styleUrls: ['./league-editar.component.css']
})
export class LeagueEditarComponent implements OnInit {

	private id: number;
	private league: League;

	/**
	 * Construtor.
	 *
	 * @param ActivatedRoute route
	 * @param Router router
	 * @param LeagueService leagueService
	 */
	constructor(
		private route: ActivatedRoute, 
		private router: Router, 
		private leagueService: LeagueService) {
	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {
		this.id = +this.route.snapshot.params['id'];
		this.league = this.leagueService.buscarPorId(this.id);
	}

	/**
	 * Método responsável por atualizar os dados de um league.
	 */
	atualizar() {
//		this.leagueService.atualizar(this.id, this.league);
		this.router.navigate(['/leagues']);
	}
}