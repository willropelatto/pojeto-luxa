import { Http } from '@angular/http';
import { Injectable } from '@angular/core';

import { Team } from './team.model';

import { Observable } from 'rxjs/Rx';

import { HttpUtilService } from './../../util/http-util-service';

@Injectable()
export class TeamService {

	private teams: Team[];

	private path = 'team';

	constructor(private http: Http, private httpUtil: HttpUtilService) {
	}


	/**
	 * Retorna listagem de todos os teams.
	 *
	 * @return Team[] teams
	 */
	listarTodos(): Observable<Team[]> {

		return this.http.get(this.httpUtil.url(this.path + '/list'), this.httpUtil.headers())
			.map(this.httpUtil.extrairDados)
			.catch(this.httpUtil.processarErros);
	}


	register(team: Team): Observable<Team> {
		let params = JSON.parse(JSON.stringify(team || null));

		return this.http.post(this.httpUtil.url(this.path + '/register'), params,
			this.httpUtil.headers())
			.map(this.httpUtil.extrairDadosCadastro)
			.catch(this.httpUtil.processarErros);
		
	}


	update(team: Team) {
		let params = JSON.stringify(team);

		return this.http.put(this.httpUtil.url(this.path + '/update'), params,
			this.httpUtil.headers())
			.map(this.httpUtil.extrairDados)
			.catch(this.httpUtil.processarErros);
	}


		/**
	 * Retorna os dados de um player por id.
	 *
	 * @param number id
	 * @return Usuario player
	 */
	buscarPorIdUser(id: number): Observable<Team> {
		let teamPath = this.path + '/listTeam';
		return this.http.get(this.httpUtil.url(teamPath + '/' + id),
						this.httpUtil.headers())
	                .map(this.httpUtil.extrairDados)
	                .catch(this.httpUtil.processarErros);
	}


	/**
	 * Retorna os dados de um team por id.
	 *
	 * @param number id
	 * @return Usuario team
	 */
/*	buscarPorId(id: number): Team {
		var teams: Team[] = this.listarTodos();
		for (let team of teams) {
			if (team.id == id) {
				return team;
			}
		}

		return new Team();
	}*/

	/**
	 * Atualiza os dados de um team.
	 *
	 * @param number id
	 * @param Team team
	 */
/*	atualizar(id: number, team: Team): void {
		var teams: Team[] = this.listarTodos();
		for (var i = 0; i < teams.length; i++) {
			if (teams[i].id == id) {
				teams[i] = team;
			}
		}

		sessionStorage['teams'] = JSON.stringify(teams);
	}*/

	/**
	 * Remove um team.
	 *
	 * @param number id
	 */
/*	excluir(id: number): void {
		var teams: Team[] = this.listarTodos();
		var teamsFinal: Team[] = [];

		for (let team of teams) {
			if (team.id != id) {
				teamsFinal.push(team);
			}
		}

		sessionStorage['teams'] = JSON.stringify(teamsFinal);
	}
*/
	/**
	 * Retorna listagem parcial de teams.
	 *
	 * @param number pagina
	 * @param number qtdPorPagina
	 * @return Team[] teams
	 */
	listarParcial(pagina: number, qtdPorPagina: number): Team[] {
		let storage: string = sessionStorage['teams'];
		let teams: Team[] = storage ? JSON.parse(storage) : [];

		let teamsParcial: Team[] = [];
		for (let i = (pagina * qtdPorPagina); i < (pagina * qtdPorPagina + qtdPorPagina); i++) {
			if (i >= teams.length) {
				break;
			}
			teamsParcial.push(teams[i]);
		}

		return teamsParcial;
	}

	/**
	 * Retorna o total de pessoas.
	 *
	 * @return number total de registros
	 */
	totalRegistros(): number {
		return 0;// this.listarTodos().length;
	}


}
