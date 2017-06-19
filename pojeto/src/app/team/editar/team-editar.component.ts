/**
 * Componente de edição de team.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router'; 

import { Team, TeamService } from '../shared';

@Component({
	selector: 'kz-team-editar',
	templateUrl: './team-editar.component.html',
	styleUrls: ['./team-editar.component.css']
})
export class TeamEditarComponent implements OnInit {

	public id: number;
	public team: Team;

	/**
	 * Construtor.
	 *
	 * @param ActivatedRoute route
	 * @param Router router
	 * @param TeamService teamService
	 */
	constructor(
		public route: ActivatedRoute, 
		public router: Router, 
		public teamService: TeamService) {
	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {
		this.id = +this.route.snapshot.params['id'];
	//	this.team = this.teamService.buscarPorId(this.id);
	}

	/**
	 * Método responsável por atualizar os dados de um team.
	 */
	atualizar() {
	//	this.teamService.atualizar(this.id, this.team);
		this.router.navigate(['/teams']);
	}
}