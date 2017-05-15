/**
 * Model de bidinfos.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

export class Bidinfo {
	constructor(
		public id?: number,
		public bidValue?: number,
		public teamId?: number,
		public originalValue?: number,
		public playerId?: number,
		public bidAproved?: boolean){}
}