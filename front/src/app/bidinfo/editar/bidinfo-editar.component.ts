/**
 * Componente de edição de bidinfo.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router'; 

import { Bidinfo, BidinfoService } from '../shared';

@Component({
	selector: 'kz-bidinfo-editar',
	templateUrl: './bidinfo-editar.component.html',
	styleUrls: ['./bidinfo-editar.component.css']
})
export class BidinfoEditarComponent implements OnInit {

	private id: number;
	private bidinfo: Bidinfo;

	/**
	 * Construtor.
	 *
	 * @param ActivatedRoute route
	 * @param Router router
	 * @param BidinfoService bidinfoService
	 */
	constructor(
		private route: ActivatedRoute, 
		private router: Router, 
		private bidinfoService: BidinfoService) {
	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {
		this.id = +this.route.snapshot.params['id'];
		this.bidinfo = this.bidinfoService.buscarPorId(this.id);
	}

	/**
	 * Método responsável por atualizar os dados de um bidinfo.
	 */
	atualizar() {
		this.bidinfoService.atualizar(this.id, this.bidinfo);
		this.router.navigate(['/bidinfos']);
	}
}