/**
 * Componente de cadastro de teams.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Router } from '@angular/router'; 

import { Team, TeamService } from '../shared';

@Component({
	selector: 'kz-team-cadastrar',
	templateUrl: './team-cadastrar.component.html',
	styleUrls: ['./team-cadastrar.component.css']
})
export class TeamCadastrarComponent implements OnInit {

	private team: Team;

	/**
	 * Construtor.
	 *
	 * @param Router router
	 * @param TeamService teamService
	 */
	constructor(
		private router: Router, 
		private teamService: TeamService) {
	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {
		this.team = new Team();
	}

	/**
	 * Método responsável por cadastrar um novo team.
	 */
	cadastrar() {
		this.team.id = new Date().getTime();
		this.teamService.cadastrar(this.team);
		this.router.navigate(['/teams']);
	}
}