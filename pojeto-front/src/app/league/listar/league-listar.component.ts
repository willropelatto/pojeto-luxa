/**
 * Componente de listagem de leagues.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { League, LeagueService } from '../shared';
import { KzPaginacaoComponent } from '../../shared';

@Component({
	selector: 'kz-league-listar',
	templateUrl: './league-listar.component.html',
	styleUrls: ['./league-listar.component.css']
})
export class LeagueListarComponent implements OnInit {

	private leagues: League[];
	private idExcluir: number;
	private pagina: number;
	private totalRegistros: number;

	/**
	 * Construtor.
	 *
	 * @param LeagueService leagueService
	 */
	constructor(private leagueService: LeagueService,
		private route: ActivatedRoute) {
	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {
		this.totalRegistros = this.leagueService.totalRegistros();
		this.pagina = +this.route.snapshot.queryParams['pagina'] || KzPaginacaoComponent.PAG_PADRAO;
		this.leagues = this.leagueService.listarParcial(
			--this.pagina, KzPaginacaoComponent.TOTAL_PAGS_PADRAO);
	}

	/**
	 * Método responsável por armazenar o id do league a 
	 * removido.
	 *
	 * @param number id
	 */
	excluir(id: number) {
 		this.idExcluir = id;
 	}

 	/**
	 * Método responsável por remover um league.
	 */
 	onExcluir() {
 		this.leagueService.excluir(this.idExcluir);
 		location.reload();
 	}

 	/**
 	 * Método responsável pela paginação.
 	 *
 	 * @param any $event Número da página atual.
 	 */
 	paginar($event: any) {
		this.pagina = $event - 1;
		this.totalRegistros = this.leagueService.totalRegistros();
		this.leagues = this.leagueService.listarParcial(
			this.pagina, KzPaginacaoComponent.TOTAL_PAGS_PADRAO);
	}
}