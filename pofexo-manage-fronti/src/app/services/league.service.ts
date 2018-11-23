import { Injectable } from '@angular/core';
import { Configs } from './configs';
import { League } from '../beans/league';
import { tap, catchError } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LeagueService {

  constructor(private http: HttpClient) {
  }

  getLeagues(currentPage: any, pageSize: any) {
    let options = {
      headers: Configs.getHttpHeaders(),
      params: Configs.getHttpParams(currentPage, pageSize)
    };

    return this.http
      .get<League[]>(Configs.leagueUrl + '/list', options)
      .pipe(
        tap(leagues => Configs.log('fetched leagues')),
        catchError(Configs.handleError("getLeagues", []))
      );
  }

  getLeague(id: number) {
    let options = {
      headers: Configs.getHttpHeaders()
    };
    
    return this.http
      .get<League>(`${Configs.leagueUrl}/getLeague/${id}`, options)
      .pipe(
        tap(_ => Configs.log(`fetched League id=${id}`)),
        catchError(Configs.handleError<League>(`getLeague id=${id}`))
      );
  }


}
