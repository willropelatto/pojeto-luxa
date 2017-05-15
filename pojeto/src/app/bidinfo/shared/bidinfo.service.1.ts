/**
 * Serviço de gerenciamento de bidinfos.
 *
 * @author Pojeto
 * @since 0.0.3
 */
 
import { Injectable } from '@angular/core';

import { Bidinfo } from './bidinfo.model';

import { Http, Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';

import { HttpUtilService } from '../../util';


@Injectable()
export class BidinfoService {

	private path = 'bidinfo';
	private msgErro:string;
	private bidinfos: Bidinfo[];

	constructor(private http: Http, private httpUtil: HttpUtilService) {
	}

	/**
	 * Retorna listagem de todos os bidinfos.
	 *
	 * @return Bidinfo[] bidinfos
	 */
	listarTodos(): Observable<Bidinfo[]> {

		return this.http.get(this.httpUtil.url(this.path), this.httpUtil.headers())
	                .map(this.httpUtil.extrairDados)
	                .catch(this.httpUtil.processarErros);
	}

	/**
	 * Cadastra um novo bidinfo.
	 *
	 * @param Bidinfo bidinfo
	 */
	cadastrar(bidinfo: Bidinfo): Observable<Bidinfo> {
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
	buscarPorId(id: number): Observable<Bidinfo> {
		return this.http.get(this.httpUtil.url(this.path + '/' + id),
						this.httpUtil.headers())
	                .map(this.httpUtil.extrairDados)
	                .catch(this.httpUtil.processarErros);
	}

	/**
	 * Atualiza os dados de um bidinfo.
	 *
	 * @param number id
	 * @param Bidinfo bidinfo
	 */

	atualizar(bidinfo: Bidinfo) {
		let params = JSON.stringify(bidinfo);

    	return this.http.put(this.httpUtil.url(this.path), params,
    					this.httpUtil.headers())
      				.map(this.httpUtil.extrairDados)
	                .catch(this.httpUtil.processarErros);
	}
	/**
	 * Remove um bidinfo.
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
	 * Retorna listagem parcial de bidinfos.
	 *
	 * @param number pagina
	 * @param number qtdPorPagina
	 * @return Bidinfo[] bidinfos
	 */
	listarParcial(pagina: number, qtdPorPagina: number): Bidinfo[] {
		
		this.listarTodos()
					.subscribe(bidinfos => this.bidinfos = bidinfos,
                				error  => this.msgErro = error);
			

		let bidinfosParcial: Bidinfo[] = [];
		for (let i = ( pagina * qtdPorPagina ); i < (pagina * qtdPorPagina + qtdPorPagina); i++) {
			if (i >= this.bidinfos.length) {
				break;
			}
			bidinfosParcial.push(this.bidinfos[i]);
		}

		return bidinfosParcial;
	}

	/**
	 * Retorna o total de pessoas.
	 *
	 * @return number total de registros
	 */
	totalRegistros(): number {
		this.listarTodos()
					.subscribe(bidinfos => this.bidinfos = bidinfos,
                				error  => this.msgErro = error);
		return this.bidinfos.length;
	}
}
