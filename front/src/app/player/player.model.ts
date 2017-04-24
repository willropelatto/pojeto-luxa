/**
 * Model de players.
 *
 * @since 0.0.3
 */

export class Player {
	constructor(
		public id?: number,
		public name?: string,
		public position?: string,
		public baseId?: number,
		public rating?: number){}
}