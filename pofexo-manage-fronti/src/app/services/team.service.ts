import { Injectable, Output, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Team } from '../beans/team';
import { Configs } from './configs';
import { tap, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TeamService {

  @Output() budgetEvent: EventEmitter<number> = new EventEmitter();

  constructor(private http: HttpClient
  ) { }

  getTeamList(currentPage: any, pageSize: any) {
    let options = {
      headers: Configs.getHttpHeaders(),
      params: Configs.getHttpParams(currentPage, pageSize)
    };

    return this.http
      .get<Team[]>(Configs.teamUrl + '/list', options)
      .pipe(
        tap(players => Configs.log('fetched Team')),
        catchError(Configs.handleError("getTeamList", []))
      );
  }

  getTeamId(id: number): Observable<Team> {
    let options = {
      headers: Configs.getHttpHeaders()
    };

    return this.http
      .get<Team>(`${Configs.teamUrl}/getTeam/${id}`, options)
      .pipe(
        tap(tm => {
          if (tm !== null) {
            this.budgetEvent.emit(tm.budget);
            localStorage.setItem('currentTeam', JSON.stringify(tm));
            Configs.log(`fetched Team id=${id}`);
          }
        }),
        catchError(Configs.handleError<Team>(`getTeam id=${id}`))
      );
  }

  getTeamUser(user: number): Observable<Team> {
    let options = {
      headers: Configs.getHttpHeaders()
    };

    return this.http
      .get<Team>(`${Configs.teamUrl}/getByUser/${user}`, options)
      .pipe(
        tap(tm => {
          if (tm !== null) {
            this.budgetEvent.emit(tm.budget);
            localStorage.setItem('currentTeam', JSON.stringify(tm));
            Configs.log(`fetched user id=${user}`);
          }
        }

        ),
        catchError(Configs.handleError<Team>(`getTeam id=${user}`))
      );
  }

  getTeamPlayers(id: number): Observable<Team> {
    let options = {
      headers: Configs.getHttpHeaders()
    };

    return this.http
      .get<Team>(`${Configs.teamUrl}/getTeam/${id}/contract`, options)
      .pipe(
        tap(_ => Configs.log(`fetched Team id=${id}`)),
        catchError(Configs.handleError<Team>(`getTeam id=${id}`))
      );
  }

  getTeamPlayersBid(id: number): Observable<Team> {
    let options = {
      headers: Configs.getHttpHeaders()
    };

    return this.http
      .get<Team>(`${Configs.teamUrl}/getTeam/${id}/onbid`, options)
      .pipe(
        tap(_ => Configs.log(`fetched Team id=${id}`)),
        catchError(Configs.handleError<Team>(`getTeam id=${id}`))
      );
  }

  addTeam(tm: Team): Observable<Team> {
    return this.http
      .post<Team>(Configs.teamUrl + '/register', tm, Configs.getHttpOptions())
      .pipe(
        tap((tm: Team) => Configs.log(`added Team w/ id=${tm.id}`)),
        catchError(Configs.handleError<Team>('addTeam'))
      );
  }

  getCurrentTeam(): Team {
    let team: Team;
    team = JSON.parse(localStorage.getItem('currentTeam'));
    if (team !== null) {
      this.budgetEvent.emit(team.budget);
    }    
    return team;
  }


}
