/**
 * Componente de listagem de teams.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Team, TeamService } from '../shared';
import { KzPaginacaoComponent } from '../../shared';

@Component({
	selector: 'kz-team-listar',
	templateUrl: './team-listar.component.html',
	styleUrls: ['./team-listar.component.css']
})
export class TeamListarComponent implements OnInit {

	private teams: Team[];
	private idExcluir: number;
	private pagina: number;
	private totalRegistros: number;

	/**
	 * Construtor.
	 *
	 * @param TeamService teamService
	 */
	constructor(private teamService: TeamService,
		private route: ActivatedRoute) {
	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {
		this.totalRegistros = this.teamService.totalRegistros();
		this.pagina = +this.route.snapshot.queryParams['pagina'] || KzPaginacaoComponent.PAG_PADRAO;
		this.teams = this.teamService.listarParcial(
			--this.pagina, KzPaginacaoComponent.TOTAL_PAGS_PADRAO);
	}

	/**
	 * Método responsável por armazenar o id do team a 
	 * removido.
	 *
	 * @param number id
	 */
	excluir(id: number) {
 		this.idExcluir = id;
 	}

 	/**
	 * Método responsável por remover um team.
	 */
 	onExcluir() {
 		this.teamService.excluir(this.idExcluir);
 		location.reload();
 	}

 	/**
 	 * Método responsável pela paginação.
 	 *
 	 * @param any $event Número da página atual.
 	 */
 	paginar($event: any) {
		this.pagina = $event - 1;
		this.totalRegistros = this.teamService.totalRegistros();
		this.teams = this.teamService.listarParcial(
			this.pagina, KzPaginacaoComponent.TOTAL_PAGS_PADRAO);
	}
}