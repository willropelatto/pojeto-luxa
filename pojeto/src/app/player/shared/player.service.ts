import { PlayerFilter } from './../../transfermarket/shared/playerFilter.model';
/**
 * Serviço de gerenciamento de players.
 *
 * @author Pojeto
 * @since 0.0.3
 */

import { Injectable } from '@angular/core';

import { Player } from './player.model';

import { Http, Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';

import { HttpUtilService } from '../../util';


@Injectable()
export class PlayerService {

	private path = 'player/listPlayer/7';
	private msgErro: string;
	private players: Player[];

	constructor(private http: Http, private httpUtil: HttpUtilService) {
	}

	/**
	 * Retorna listagem de todos os players.
	 *
	 * @return Player[] players
	 */
	listarTodos(): Observable<Player[]> {
		return this.http.get(this.httpUtil.url(this.path), this.httpUtil.headers())
			.map(this.httpUtil.extrairDados)
			.catch(this.httpUtil.processarErros);
	}


	listarFiltro(playerFilter : PlayerFilter): Observable<Player[]> {
		let params = JSON.stringify(playerFilter);

		return this.http.get(this.httpUtil.url(this.path + '/' + params),   
			this.httpUtil.headers())
			.map(this.httpUtil.extrairDados)
			.catch(this.httpUtil.processarErros);
	}

	/**
	 * Cadastra um novo player.
	 *
	 * @param Player player
	 */
	cadastrar(player: Player): Observable<Player> {
		let params = JSON.stringify(player);

		return this.http.post(this.httpUtil.url(this.path), params,
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
	buscarPorId(id: number): Observable<Player> {
		return this.http.get(this.httpUtil.url(this.path + '/' + id),
			this.httpUtil.headers())
			.map(this.httpUtil.extrairDados)
			.catch(this.httpUtil.processarErros);
	}

	/**
	 * Atualiza os dados de um player.
	 *
	 * @param number id
	 * @param Player player
	 */

	atualizar(player: Player) {
		let params = JSON.stringify(player);

		return this.http.put(this.httpUtil.url(this.path), params,
			this.httpUtil.headers())
			.map(this.httpUtil.extrairDados)
			.catch(this.httpUtil.processarErros);
	}
	/**
	 * Remove um player.
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
	 * Retorna listagem parcial de players.
	 *
	 * @param number pagina
	 * @param number qtdPorPagina
	 * @return Player[] players
	 */
	listarParcial(pagina: number, qtdPorPagina: number): Player[] {

		this.listarTodos()
			.subscribe(players => this.players = players,
			error => this.msgErro = error);


		let playersParcial: Player[] = [];
		/*	for (let i = ( pagina * qtdPorPagina ); i < (pagina * qtdPorPagina + qtdPorPagina); i++) {
				if (i >= this.players.length) {
					break;
				}
				playersParcial.push(this.players[i]);
			}*/

		return playersParcial;
	}

	/**
	 * Retorna o total de pessoas.
	 *
	 * @return number total de registros
	 */
	totalRegistros(): number {
		this.listarTodos()
			.subscribe(players => this.players = players,
			error => this.msgErro = error);
		return 100;
	}
}
