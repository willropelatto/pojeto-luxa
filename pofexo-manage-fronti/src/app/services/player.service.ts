import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Player } from '../beans/player';
import { tap, catchError } from 'rxjs/operators';
import { Configs } from './configs';
import { PlayerFilter } from '../beans/player-filter';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  constructor(private http: HttpClient) { }

  getPlayers(currentPage: any, pageSize: any) {
    let options = {
      headers: Configs.getHttpHeaders(),
      params: Configs.getHttpParams(currentPage, pageSize)
    };

    return this.http
      .get<Player[]>(Configs.playerUrl + '/list', options)
      .pipe(
        tap(players => Configs.log('fetched heroes')),
        catchError(Configs.handleError("getPlayers", []))
      );
  }

  getPlayer(id: number) {
    let options = {
      headers: Configs.getHttpHeaders()
    };

    return this.http.get<Player>(`${Configs.playerUrl}/get/${id}`, options)
      .pipe(
        tap(_ => Configs.log(`fetched Player id=${id}`)),
        catchError(Configs.handleError<Player>(`getPlayer id=${id}`))
      );
  }

  getPlayersName(name: string, currentPage: any, pageSize: any) {
    let options = {
      headers: Configs.getHttpHeaders(),
      params: Configs.getHttpParams(currentPage, pageSize)
    };

    return this.http
      .get<Player[]>(`${Configs.playerUrl}/getByName/${name}`, options)
      .pipe(
        tap(players => Configs.log(`fetched Players name=${name}`)),
        catchError(Configs.handleError<Player>(`getPlayerName name=${name}`))
      );
  }


  getFilteredPlayers(currentPage: any, pageSize: any, filter: PlayerFilter) {
    let options = {
      headers: Configs.getHttpHeaders(),
      params: Configs.getHttpParams(currentPage, pageSize)          
    };    

    return this.http
      .post<Player[]>(Configs.playerUrl + '/getPlayers', filter, options)
      .pipe(
        tap(players => Configs.log('fetched heroes')),
        catchError(Configs.handleError("getPlayers", []))
      );
  }

}
