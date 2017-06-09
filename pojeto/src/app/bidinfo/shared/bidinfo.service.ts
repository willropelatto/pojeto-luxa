import { Observable } from 'rxjs/Rx';
/**
 * Serviço de gerenciamento de bidinfos.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Injectable } from '@angular/core';

import { Bidinfo } from './bidinfo.model';

import { Http, Response } from '@angular/http';

import { HttpUtilService } from '../../util';


@Injectable()
export class BidinfoService {

	private path = 'bidinfo';
	private msgErro: string;
	private bidinfos: Bidinfo[];
	private pathApi = 'market';
	public obBidInfo: Bidinfo;

	constructor(private http: Http, private httpUtil: HttpUtilService) {
	}


	/**
 * Retorna listagem de todos os teams.
 *
 * @return Bidinfo[] Bidinfo
 */
	listarTodosBids(): Observable<Bidinfo[]> {

		return this.http.get(this.httpUtil.url(this.pathApi + '/list'), this.httpUtil.headers())
			.map(this.httpUtil.extrairDados)
			.catch(this.httpUtil.processarErros);
	}

	initialBid(bidInfo: Bidinfo): Observable<Bidinfo> {
		console.log(bidInfo);
		let params = JSON.parse(JSON.stringify(bidInfo || null));

		return this.http.post(this.httpUtil.url(this.pathApi + '/initialBid'), params,
			this.httpUtil.headers())
			.map(this.httpUtil.extrairDadosBidInfo)
			.catch(this.httpUtil.processarErros);

	}

	placeBid(bidInfo: Bidinfo) {
		let params = JSON.stringify(bidInfo || null);

		return this.http.post(this.httpUtil.url(this.pathApi + '/placeBid'), params,
			this.httpUtil.headers())
			.map(this.httpUtil.extrairDadosBidInfo)
			.catch(this.httpUtil.processarErros);
	}

	buscarPorIdPlayers(id: number): Observable<Bidinfo> {
		let bidinfoPath = this.pathApi + '/getBidFromPlayerId';
		return this.http.get(this.httpUtil.url(bidinfoPath + '/' + id),
			this.httpUtil.headers())
			.map(this.httpUtil.extrairDados)
			.catch(this.httpUtil.processarErros);
	}

	closeMarket(): Observable<Bidinfo> {
		console.log('close');
		let bidinfoPath = this.pathApi + '/close';
		return this.http.post(this.httpUtil.url(bidinfoPath),
			this.httpUtil.headers())
			.map(this.httpUtil.extrairDados)
			.catch(this.httpUtil.processarErros);
	}

	buscarPorTeam(id: number): Observable<Bidinfo[]> {
		let bidinfoPath = this.pathApi + '/getBidFromTeamId';
		return this.http.get(this.httpUtil.url(bidinfoPath + '/' + id),
			this.httpUtil.headers())
			.map(this.httpUtil.extrairDados)
			.catch(this.httpUtil.processarErros);
	}





	/**
	 * Retorna listagem de todos os bidinfos.
	 *
	 * @return Bidinfo[] bidinfos
	 */
	listarTodos(): Bidinfo[] {
		var bidinfos: string = sessionStorage['bidinfos'];
		return bidinfos ? JSON.parse(bidinfos) : [];
	}

	/**
	 * Cadastra um novo bidinfo.
	 *
	 * @param Bidinfo bidinfo
	 */
	cadastrar(bidinfo: Bidinfo): void {
		var bidinfos: Bidinfo[] = this.listarTodos();
		bidinfos.push(bidinfo);
		sessionStorage['bidinfos'] = JSON.stringify(bidinfos);
	}

	/**
	 * Cadastra um novo bidinfo.
	 *
	 * @param Bidinfo bidinfo
	 */
	cadastrarHttp(bidinfo: Bidinfo): Observable<Bidinfo> {
		let params = JSON.stringify(bidinfo);

		return this.http.post(this.httpUtil.url(this.path), params,
			this.httpUtil.headers())
			.map(this.httpUtil.extrairDados)
			.catch(this.httpUtil.processarErros);
	}


	/**
	 * Retorna os dados de um bidinfo por id.
	 *
	 * @param number id
	 * @return Usuario bidinfo
	 */
	buscarPorId(id: number): Bidinfo {
		var bidinfos: Bidinfo[] = this.listarTodos();
		for (let bidinfo of bidinfos) {
			if (bidinfo.id == id) {
				return bidinfo;
			}
		}

		return new Bidinfo();
	}

	buscarPorIdPlayer(id: number): Bidinfo {
		var bidinfos: Bidinfo[] = this.listarTodos();
		for (let bidinfo of bidinfos) {
			if (bidinfo.playerID == id) {
				return bidinfo;
			}
		}

		return null;
	}

	/**
	 * Atualiza os dados de um bidinfo.
	 *
	 * @param number id
	 * @param Bidinfo bidinfo
	 */
	atualizar(id: number, bidinfo: Bidinfo): void {
		var bidinfos: Bidinfo[] = this.listarTodos();
		for (var i = 0; i < bidinfos.length; i++) {
			if (bidinfos[i].id == id) {
				bidinfos[i] = bidinfo;
			}
		}

		sessionStorage['bidinfos'] = JSON.stringify(bidinfos);
	}

	/**
	 * Remove um bidinfo.
	 *
	 * @param number id
	 */
	excluir(id: number): void {
		var bidinfos: Bidinfo[] = this.listarTodos();
		var bidinfosFinal: Bidinfo[] = [];

		for (let bidinfo of bidinfos) {
			if (bidinfo.id != id) {
				bidinfosFinal.push(bidinfo);
			}
		}

		sessionStorage['bidinfos'] = JSON.stringify(bidinfosFinal);
	}

	/**
	 * Retorna listagem parcial de bidinfos.
	 *
	 * @param number pagina
	 * @param number qtdPorPagina
	 * @return Bidinfo[] bidinfos
	 */
	listarParcial(pagina: number, qtdPorPagina: number): Bidinfo[] {
		let storage: string = sessionStorage['bidinfos'];
		let bidinfos: Bidinfo[] = storage ? JSON.parse(storage) : [];

		let bidinfosParcial: Bidinfo[] = [];
		for (let i = (pagina * qtdPorPagina); i < (pagina * qtdPorPagina + qtdPorPagina); i++) {
			if (i >= bidinfos.length) {
				break;
			}
			bidinfosParcial.push(bidinfos[i]);
		}

		return bidinfosParcial;
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
