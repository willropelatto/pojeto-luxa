/**
 * Serviço de gerenciamento de users.
 *
 * @author Pojeto
 * @since 0.0.0
 */
 
import { Injectable } from '@angular/core';

import { User } from './user.model';

@Injectable()
export class UserService {

	/**
	 * Retorna listagem de todos os users.
	 *
	 * @return User[] users
	 */
	listarTodos(): User[] {
		var users:string = sessionStorage['users'];
		return users ? JSON.parse(users) : [];
	}

	/**
	 * Cadastra um novo user.
	 *
	 * @param User user
	 */
	cadastrar(user: User): void {
		var users:User[] = this.listarTodos();
		users.push(user);
		sessionStorage['users'] = JSON.stringify(users);
	}

	/**
	 * Retorna os dados de um user por id.
	 *
	 * @param number id
	 * @return Usuario user
	 */
	buscarPorId(id: number):User {
		var users:User[] = this.listarTodos();
		for (let user of users) {
			if (user.id == id) {
				return user;
			}
		}

		return new User();
	}

	/**
	 * Atualiza os dados de um user.
	 *
	 * @param number id
	 * @param User user
	 */
	atualizar(id: number, user: User): void {
		var users:User[] = this.listarTodos();
		for (var i=0; i<users.length; i++) {
			if (users[i].id == id) {
				users[i] = user;
			}
		}

		sessionStorage['users'] = JSON.stringify(users);
	}

	/**
	 * Remove um user.
	 *
	 * @param number id
	 */
	excluir(id: number): void {
		var users:User[] = this.listarTodos();
		var usersFinal:User[] = [];

		for (let user of users) {
			if (user.id != id) {
				usersFinal.push(user);
			}
		}

		sessionStorage['users'] = JSON.stringify(usersFinal);
	}

	/**
	 * Retorna listagem parcial de users.
	 *
	 * @param number pagina
	 * @param number qtdPorPagina
	 * @return User[] users
	 */
	listarParcial(pagina: number, qtdPorPagina: number): User[] {
		let storage: string = sessionStorage['users'];
		let users: User[] = storage ? JSON.parse(storage) : [];

		let usersParcial: User[] = [];
		for (let i = ( pagina * qtdPorPagina ); i < (pagina * qtdPorPagina + qtdPorPagina); i++) {
			if (i >= users.length) {
				break;
			}
			usersParcial.push(users[i]);
		}

		return usersParcial;
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
