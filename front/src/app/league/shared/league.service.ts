/**
 * Serviço de gerenciamento de leagues.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */
 
import { Injectable } from '@angular/core';

import { League } from './league.model';

@Injectable()
export class LeagueService {

	/**
	 * Retorna listagem de todos os leagues.
	 *
	 * @return League[] leagues
	 */
	listarTodos(): League[] {
		var leagues:string = sessionStorage['leagues'];
		return leagues ? JSON.parse(leagues) : [];
	}

	/**
	 * Cadastra um novo league.
	 *
	 * @param League league
	 */
	cadastrar(league: League): void {
		var leagues:League[] = this.listarTodos();
		leagues.push(league);
		sessionStorage['leagues'] = JSON.stringify(leagues);
	}

	/**
	 * Retorna os dados de um league por id.
	 *
	 * @param number id
	 * @return Usuario league
	 */
	buscarPorId(id: number):League {
		var leagues:League[] = this.listarTodos();
		for (let league of leagues) {
			if (league.id == id) {
				return league;
			}
		}

		return new League();
	}

	/**
	 * Atualiza os dados de um league.
	 *
	 * @param number id
	 * @param League league
	 */
	atualizar(id: number, league: League): void {
		var leagues:League[] = this.listarTodos();
		for (var i=0; i<leagues.length; i++) {
			if (leagues[i].id == id) {
				leagues[i] = league;
			}
		}

		sessionStorage['leagues'] = JSON.stringify(leagues);
	}

	/**
	 * Remove um league.
	 *
	 * @param number id
	 */
	excluir(id: number): void {
		var leagues:League[] = this.listarTodos();
		var leaguesFinal:League[] = [];

		for (let league of leagues) {
			if (league.id != id) {
				leaguesFinal.push(league);
			}
		}

		sessionStorage['leagues'] = JSON.stringify(leaguesFinal);
	}

	/**
	 * Retorna listagem parcial de leagues.
	 *
	 * @param number pagina
	 * @param number qtdPorPagina
	 * @return League[] leagues
	 */
	listarParcial(pagina: number, qtdPorPagina: number): League[] {
		let storage: string = sessionStorage['leagues'];
		let leagues: League[] = storage ? JSON.parse(storage) : [];

		let leaguesParcial: League[] = [];
		for (let i = ( pagina * qtdPorPagina ); i < (pagina * qtdPorPagina + qtdPorPagina); i++) {
			if (i >= leagues.length) {
				break;
			}
			leaguesParcial.push(leagues[i]);
		}

		return leaguesParcial;
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
