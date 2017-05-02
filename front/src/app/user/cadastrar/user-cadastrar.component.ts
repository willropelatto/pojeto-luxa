/**
 * Componente de cadastro de users.
 *
 * @author Pojeto
 * @since 0.0.0
 */

import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Router } from '@angular/router'; 

import { User, UserService } from '../shared';

@Component({
	selector: 'kz-user-cadastrar',
	templateUrl: './user-cadastrar.component.html',
	styleUrls: ['./user-cadastrar.component.css']
})
export class UserCadastrarComponent implements OnInit {

	private user: User;

	/**
	 * Construtor.
	 *
	 * @param Router router
	 * @param UserService userService
	 */
	constructor(
		private router: Router, 
		private userService: UserService) {
	}

	/**
	 * Método executado logo após a criação do componente.
	 */
	ngOnInit() {
		this.user = new User();
	}

	/**
	 * Método responsável por cadastrar um novo user.
	 */
	cadastrar() {
		this.user.id = new Date().getTime();
		this.userService.cadastrar(this.user);
		this.router.navigate(['/users']);
	}
}