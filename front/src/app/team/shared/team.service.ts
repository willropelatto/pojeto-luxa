/**
 * Serviço de gerenciamento de teams.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */
 
import { Injectable } from '@angular/core';

import { Team } from './team.model';



@Injectable()
export class TeamService {

	private teams: Team[];
	

	/**
	 * Retorna listagem de todos os teams.
	 *
	 * @return Team[] teams
	 */
	listarTodos(): Team[] {
		var teams:string = sessionStorage['teams'];
		return teams ? JSON.parse(teams) : [];
	}

	/**
	 * Cadastra um novo team.
	 *
	 * @param Team team
	 */
	cadastrar(team: Team): void {
		var teams:Team[] = this.listarTodos();
		teams.push(team);
		sessionStorage['teams'] = JSON.stringify(teams);
	}

	/**
	 * Retorna os dados de um team por id.
	 *
	 * @param number id
	 * @return Usuario team
	 */
	buscarPorId(id: number):Team {
		var teams:Team[] = this.listarTodos();
		for (let team of teams) {
			if (team.id == id) {
				return team;
			}
		}

		return new Team();
	}

	/**
	 * Atualiza os dados de um team.
	 *
	 * @param number id
	 * @param Team team
	 */
	atualizar(id: number, team: Team): void {
		var teams:Team[] = this.listarTodos();
		for (var i=0; i<teams.length; i++) {
			if (teams[i].id == id) {
				teams[i] = team;
			}
		}

		sessionStorage['teams'] = JSON.stringify(teams);
	}

	/**
	 * Remove um team.
	 *
	 * @param number id
	 */
	excluir(id: number): void {
		var teams:Team[] = this.listarTodos();
		var teamsFinal:Team[] = [];

		for (let team of teams) {
			if (team.id != id) {
				teamsFinal.push(team);
			}
		}

		sessionStorage['teams'] = JSON.stringify(teamsFinal);
	}

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
		for (let i = ( pagina * qtdPorPagina ); i < (pagina * qtdPorPagina + qtdPorPagina); i++) {
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
		return this.listarTodos().length;
	}


}
