import { User } from './../../user/shared/user.model';
/**
 * Componente de visualização de team.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Team, TeamService } from '../shared';

import { Player } from '../../player';

@Component({
	selector: 'pojeto-team-visualizar',
	templateUrl: './team-visualizar.component.html',
	styleUrls: ['./team-visualizar.component.css']
})
export class TeamVisualizarComponent implements OnInit {

	public id: number;
	public teamResult: Team;
	public team: Team;
	public user: User;
	public msgErro: string;
	public players: Player[];

	/**
	 * Construtor.
	 *
	 * @param ActivatedRoute route
	 * @param TeamService teamService
	 */
	constructor(
		public route: ActivatedRoute,
		public teamService: TeamService) {
		this.user = JSON.parse(localStorage.getItem('currentUser'));


	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {
		console.log(this.user);
		this.user = JSON.parse(localStorage.getItem('currentUser'));
		this.id = +this.route.snapshot.params['id'];
		let idUser = 0;
		if (this.id === 0) {
			idUser = this.id;
		} else {
			idUser = this.user.id;
		}

		this.teamService.buscarPorIdUser(this.user.id)
					.subscribe((team) => { 
						this.team = team;
					},
					error => this.msgErro = error);
		


	}
}