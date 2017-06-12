import { Team } from './../../team/shared/team.model';
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
		public bidAproved?: boolean,
		public hasBid?: boolean,
		public team?: Team){}	
}