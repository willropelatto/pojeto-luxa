/**
 * Serviço de gerenciamento de leagues.
 *
 * @author Pojeto
 * @since 0.0.3
 */
 
import { Injectable } from '@angular/core';

import { League } from './league.model';

import { Http, Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';

import { HttpUtilService } from '../../util';


@Injectable()
export class LeagueService {

	private path = 'league/list';
	private msgErro:string;
	private leagues: League[];

	constructor(private http: Http, private httpUtil: HttpUtilService) {
	}

	/**
	 * Retorna listagem de todos os leagues.
	 *
	 * @return League[] leagues
	 */
	listarTodos(): Observable<League[]> {
		console.log('league');
		return this.http.get(this.httpUtil.url(this.path), this.httpUtil.headers())
	                .map(this.httpUtil.extrairDados)
	                .catch(this.httpUtil.processarErros);
	}

	/**
	 * Cadastra um novo league.
	 *
	 * @param League league
	 */
	cadastrar(league: League): Observable<League> {
		let params = JSON.stringify(league);

    	return this.http.post(this.httpUtil.url(this.path), params,
    					this.httpUtil.headers())
      				.map(this.httpUtil.extrairDados)
	                .catch(this.httpUtil.processarErros);
	}


	/**
	 * Retorna os dados de um league por id.
	 *
	 * @param number id
	 * @return Usuario league
	 */
	buscarPorId(id: number): Observable<League> {
		return this.http.get(this.httpUtil.url(this.path + '/' + id),
						this.httpUtil.headers())
	                .map(this.httpUtil.extrairDados)
	                .catch(this.httpUtil.processarErros);
	}

	/**
	 * Atualiza os dados de um league.
	 *
	 * @param number id
	 * @param League league
	 */

	atualizar(league: League) {
		let params = JSON.stringify(league);

    	return this.http.put(this.httpUtil.url(this.path), params,
    					this.httpUtil.headers())
      				.map(this.httpUtil.extrairDados)
	                .catch(this.httpUtil.processarErros);
	}
	/**
	 * Remove um league.
	 *
	 * @param number id
	 */
	excluir(id: number) {

		return this.http.delete(this.httpUtil.url(this.path + '/' + id),
						this.httpUtil.headers())
	                .map(this.httpUtil.extrairDados)
	                .catch(this.httpUtil.processarErros);
	}

	/**
	 * Retorna listagem parcial de leagues.
	 *
	 * @param number pagina
	 * @param number qtdPorPagina
	 * @return League[] leagues
	 */
	listarParcial(pagina: number, qtdPorPagina: number): League[] {
		
		this.listarTodos()
					.subscribe(leagues => this.leagues = leagues,
                				error  => this.msgErro = error);
			

		let leaguesParcial: League[] = [];
	/*	for (let i = ( pagina * qtdPorPagina ); i < (pagina * qtdPorPagina + qtdPorPagina); i++) {
			if (i >= this.leagues.length) {
				break;
			}
			leaguesParcial.push(this.leagues[i]);
		}*/

		return leaguesParcial;
	}

	/**
	 * Retorna o total de pessoas.
	 *
	 * @return number total de registros
	 */
	totalRegistros(): number {
		this.listarTodos()
					.subscribe(leagues => this.leagues = leagues,
                				error  => this.msgErro = error);
		return 100;
	}
}
