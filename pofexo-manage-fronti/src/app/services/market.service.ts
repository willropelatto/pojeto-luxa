import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Configs } from './configs';
import { Observable } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { Player } from '../beans/player';

@Injectable({
  providedIn: 'root'
})
export class MarketService {

  constructor(private http: HttpClient) { }

  dismissPlayer(bid: Player): Observable<any> {
    return this.http
      .post(Configs.mktUrl + '/dismiss', bid, Configs.getHttpOptions())
      .pipe(
        tap(_ => Configs.log(`player dismmissed id=${bid.id}`)),
        catchError(Configs.handleError<any>('dismissPlayer'))
      );
  }  

  placeBid(bid: Player): Observable<any> {
    return this.http
      .post(Configs.mktUrl + '/placeBid', bid, Configs.getHttpOptions())
      .pipe(
        tap(_ => Configs.log(`bid set to player id=${bid.id}`)),
        catchError(Configs.handleError<any>('placeBid'))
      );
  }  

  openMarket(): Observable<any> {
    return this.http
      .post(Configs.mktUrl + '/open', Configs.getHttpOptions())
      .pipe(
        tap(_ => Configs.log('market is now open bitch')),
        catchError(Configs.handleError<any>('openMarket'))
      );
  } 
  
  setLeagues(leagues: number[]): Observable<any> {
    return this.http
      .post(Configs.mktUrl + '/setLeagues', leagues, Configs.getHttpOptions())
      .pipe(
        tap(_ => Configs.log(`leagues setted ids=${leagues}`)),
        catchError(Configs.handleError<any>('setLeagues'))
      );
  }    
}
