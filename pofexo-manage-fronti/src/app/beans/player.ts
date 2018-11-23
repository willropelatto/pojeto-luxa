import { Bid } from './bid';
import { NameValueClass } from './nameValueClass';

export class Player {
    id: number;
    position: string;
    name: string;
    baseId: number;
    rating: number;
    originalId: string;
    clubName: string;
    height: number;
    weight: number;
    age: number;
    foot: string;
    atkWorkRate: string;
    defWorkRate: string;
    headshotImgUrl: string;
    status: string;
    bid: Bid;
    attributes: NameValueClass[];
}


