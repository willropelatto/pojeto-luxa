/**
 * Model de teams.
 *

 */
import { Player } from '../../player';


export class Team {
	constructor(
		public id?: number,
		public name?: string,
		public players?: Player[],
		public luxa?: string){}
}