/**
 * Model de leagues.
 *
 * @author Pojeto
 * @since 0.0.3
 */

export class League {
	constructor(
		public id?: number,
		public name?: string,
		public imgUrl?:string,
		public abbrName?: string){}
}