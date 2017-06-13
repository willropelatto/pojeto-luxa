import { User } from './../../user/shared/user.model';
import { UserService } from './../../user/shared/user.service';
import { AlertService } from './../../util/alert.service';
import { Component, Input } from '@angular/core';
import { OnInit } from '@angular/core';
import { Router } from '@angular/router';


import { Team, TeamService } from '../shared';

@Component({
	selector: 'kz-team-cadastrar',
	templateUrl: './team-cadastrar.component.html',
	styleUrls: ['./team-cadastrar.component.css']
})
export class TeamCadastrarComponent implements OnInit {

	private budgetValue: number = 15000.00;

	private team: Team;
	private currentUser: User;

	/**
	 * Construtor.
	 *
	 * @param Router router
	 * @param TeamService teamService
	 */
	constructor(
		private router: Router,
		private teamService: TeamService,
		private alertService: AlertService
		) {
	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {
		this.team = new Team();
		this.team.budget = this.budgetValue;
		this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
		this.team.idUser = this.currentUser.id;		
	}

	/**
	 * Método responsável por cadastrar um novo team.
	 */
	register() {
		console.log(this.team);
		this.teamService.register(this.team)
			.subscribe(
			(res) => {
				this.router.navigate(['/']);					
				this.alertService.success('Registro Efetuado com sucesso', true);
				
			},
			(err) => {
				this.alertService.error(err);
			});
	}
	
}