/**
 * Componente de edição de user.
 *
 * @author Pojeto
 * @since 0.0.0
 */

import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router'; 

import { User, UserService } from '../shared';

@Component({
	selector: 'kz-user-editar',
	templateUrl: './user-editar.component.html',
	styleUrls: ['./user-editar.component.css']
})
export class UserEditarComponent implements OnInit {

	private id: number;
	private user: User;

	/**
	 * Construtor.
	 *
	 * @param ActivatedRoute route
	 * @param Router router
	 * @param UserService userService
	 */
	constructor(
		private route: ActivatedRoute, 
		private router: Router, 
		private userService: UserService) {
	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {
		this.id = +this.route.snapshot.params['id'];
		this.user = this.userService.buscarPorId(this.id);
	}

	/**
	 * Método responsável por atualizar os dados de um user.
	 */
	atualizar() {
		this.userService.atualizar(this.id, this.user);
		this.router.navigate(['/users']);
	}
}