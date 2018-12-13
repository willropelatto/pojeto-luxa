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

    // get atkWorkRate(): string {
    //     return this._atkWorkRate;
    // }

    // set atkWorkRate(value: string) {
    //     this._atkWorkRate = value.slice(0, 1);
    //     console.log(this._atkWorkRate)
    // }

    // get defWorkRate(): string {
    //     console.log(this._defWorkRate.slice(0,1));
    //     return this._defWorkRate;
    // }

    // set defWorkRate(value: string) {
    //     this._defWorkRate = value.slice(0, 1);
    //     console.log(this._defWorkRate)
    // }    



}


