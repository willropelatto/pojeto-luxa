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
	selector: 'kz-team-visualizar',
	templateUrl: './team-visualizar.component.html',
	styleUrls: ['./team-visualizar.component.css']
})
export class TeamVisualizarComponent implements OnInit {

	private id: number;
	private team: Team;
	private players : Player[] = [
		new Player(1,'Messi','PD',1,94),
		new Player(2,'Cristiano Ronaldo','PE',2,93)
	]

	/**
	 * Construtor.
	 *
	 * @param ActivatedRoute route
	 * @param TeamService teamService
	 */
	constructor(
		private route: ActivatedRoute, 
		private teamService: TeamService) {
	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {
		this.id = +this.route.snapshot.params['id'];
		//this.team = this.teamService.buscarPorId(this.id);
		this.team.players = this.players;
	}
}