/**
 * Componente de cadastro de bidinfos.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Router } from '@angular/router'; 

import { Bidinfo, BidinfoService } from '../shared';

import { Player, PlayerService } from '../../player';

import { Team, TeamService } from '../../team';

@Component({
	selector: 'kz-bidinfo-cadastrar',
	templateUrl: './bidinfo-cadastrar.component.html',
	styleUrls: ['./bidinfo-cadastrar.component.css']
})
export class BidinfoCadastrarComponent implements OnInit {

	private bidinfo: Bidinfo;
	private players: Player[];
	private teams: Team[];

	/**
	 * Construtor.
	 *
	 * @param Router router
	 * @param BidinfoService bidinfoService
	 */
	constructor(
		private router: Router, 
		private bidinfoService: BidinfoService,
		private playerService: PlayerService,
		private teamService: TeamService) {
	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {
		this.players = this.playerService.listarTodos();
		this.teams = this.teamService.listarTodos();
		this.bidinfo = new Bidinfo();

	}

	/**
	 * Método responsável por cadastrar um novo bidinfo.
	 */
	cadastrar() {
		this.bidinfo.id = new Date().getTime();
		this.bidinfoService.cadastrar(this.bidinfo);
		this.router.navigate(['/bidinfos']);
	}
}