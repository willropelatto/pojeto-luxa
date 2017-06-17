/**
 * Model de players.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

export class Player {
	constructor(
		public id?: number,
		public name?: string,
		public position?: string,
		public baseId?: number,
		public rating?: number,
		public hasBid?:boolean){}
}