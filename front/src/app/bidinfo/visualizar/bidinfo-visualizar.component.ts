/**
 * Componente de visualização de bidinfo.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Bidinfo, BidinfoService } from '../shared';

@Component({
	selector: 'kz-bidinfo-visualizar',
	templateUrl: './bidinfo-visualizar.component.html',
	styleUrls: ['./bidinfo-visualizar.component.css']
})
export class BidinfoVisualizarComponent implements OnInit {

	private id: number;
	private bidinfo: Bidinfo;

	/**
	 * Construtor.
	 *
	 * @param ActivatedRoute route
	 * @param BidinfoService bidinfoService
	 */
	constructor(
		private route: ActivatedRoute, 
		private bidinfoService: BidinfoService) {
	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {
		this.id = +this.route.snapshot.params['id'];
		this.bidinfo = this.bidinfoService.buscarPorId(this.id);
	}
}