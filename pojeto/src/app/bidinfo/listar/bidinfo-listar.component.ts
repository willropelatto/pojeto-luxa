import { PlayerService } from './../../player/shared/player.service';
import { Player } from './../../player/shared/player.model';
import { User } from './../../user/shared/user.model';
import { Team } from './../../team/shared/team.model';
import { TeamService } from './../../team/shared/team.service';
/**
 * Componente de listagem de bidinfos.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Bidinfo, BidinfoService } from '../shared';
import { KzPaginacaoComponent } from '../../shared';

@Component({
	selector: 'kz-bidinfo-listar',
	templateUrl: './bidinfo-listar.component.html',
	styleUrls: ['./bidinfo-listar.component.css']
})
export class BidinfoListarComponent implements OnInit {

	private bidinfos: Bidinfo[];
	private idExcluir: number;
	private pagina: number;
	private totalRegistros: number;
	private msgErro: string;
	private team: Team;
	private user: User;
	private player: Player;

	/**
	 * Construtor.
	 *
	 * @param BidinfoService bidinfoService
	 */
	constructor(private bidinfoService: BidinfoService,
		private teamService: TeamService,
		private playerService: PlayerService,
		private route: ActivatedRoute) {
		this.user = JSON.parse(localStorage.getItem('currentUser'));

	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {

		this.teamService.buscarPorIdUser(this.user.id)
			.subscribe((team) => {
				this.team = team
				this.bidinfoService.buscarPorTeam(this.team.id)
					.subscribe((bidinfo) => {
						this.bidinfos = bidinfo;

					}, error => this.msgErro = error);
			}, error => this.msgErro = error);
	}

	/**
	 * Método responsável por armazenar o id do bidinfo a 
	 * removido.
	 *
	 * @param number id
	 */
	excluir(id: number) {
		this.idExcluir = id;
	}

	/**
   * Método responsável por remover um bidinfo.
   */
	onExcluir() {
		this.bidinfoService.excluir(this.idExcluir);
		location.reload();
	}

	/**
	 * Método responsável pela paginação.
	 *
	 * @param any $event Número da página atual.
	 */
	paginar($event: any) {
		this.pagina = $event - 1;
		this.totalRegistros = this.bidinfoService.totalRegistros();
		this.bidinfos = this.bidinfoService.listarParcial(
			this.pagina, KzPaginacaoComponent.TOTAL_PAGS_PADRAO);
	}
}