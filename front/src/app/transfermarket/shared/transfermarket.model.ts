/**
 * Model de transfermarkets.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

export class Transfermarket {
	constructor(
		public idPlayer?: number,
		public name?: string,
		public position?: string,
		public rating?: number,
		public idBid?: number,
		public bidValue?: number,
		public teamId?: number,
		public originalValue?: number,
		public playerId?: number,
		public bidAproved?: boolean){}	
}