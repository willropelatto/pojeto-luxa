/**
 * Componente de visualização de user.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { User, UserService } from '../shared';

@Component({
	selector: 'kz-user-visualizar',
	templateUrl: './user-visualizar.component.html',
	styleUrls: ['./user-visualizar.component.css']
})
export class UserVisualizarComponent implements OnInit {

	private id: number;
	private user: User;

	/**
	 * Construtor.
	 *
	 * @param ActivatedRoute route
	 * @param UserService userService
	 */
	constructor(
		private route: ActivatedRoute, 
		private userService: UserService) {
	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {
		this.id = +this.route.snapshot.params['id'];
		this.user = this.userService.buscarPorId(this.id);
	}
}