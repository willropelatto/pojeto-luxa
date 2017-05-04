/**
 * Model de users.
 *
 * @author Pojeto
 * @since 0.0.0
 */

export class User {
	constructor(
		public id?: number,
		public nome?: string,
		public sobrenome?: string,
		public email?: string,
		public login?: string,
		public senha?: string){}
}



