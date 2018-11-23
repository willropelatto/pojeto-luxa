import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Manager } from '../beans/manager';
import { tap, catchError } from 'rxjs/operators';
import { Configs } from './configs';

@Injectable({
  providedIn: 'root'
})
export class ManagerService {

  constructor(private http: HttpClient) { }

  getManagerName(username: string): Observable<Manager> {
    let options = {
      headers: Configs.getHttpHeaders()
    };

    return this.http
      .get<Manager>(`${Configs.managerUrl}/getUser/${username}`, options)
      .pipe(
        tap(_ => Configs.log(`fetched manager name=${username}`)),
        catchError(Configs.handleError<Manager>(`getManagerName name=${username}`))
      );
  } 

  getListManager(currentPage: any, pageSize: any) {
    let options = {
      headers: Configs.getHttpHeaders(),
      params: Configs.getHttpParams(currentPage, pageSize)
    };

    return this.http
      .get<Manager[]>(Configs.managerUrl + '/list', options)
      .pipe(
        tap(players => Configs.log('fetched Manager')),
        catchError(Configs.handleError("getListManager", []))
      );
  }

  getManager(code: number): Observable<Manager> {
    let options = {
      headers: Configs.getHttpHeaders()
    };

    return this.http
      .get<Manager>(`${Configs.managerUrl}/get/${code}`, options)
      .pipe(
        tap(_ => Configs.log(`fetched manager id=${code}`)),
        catchError(Configs.handleError<Manager>(`getManager id=${code}`))
      );
  }

  updateManager(luxa: Manager): Observable<any> {
    return this.http
      .post(Configs.managerUrl + '/update', luxa, Configs.getHttpOptions())
      .pipe(
        tap(_ => Configs.log(`updated luxa id=${luxa.id}`)),
        catchError(Configs.handleError<any>('updateManager'))
      );
  }

  addManager(luxa: Manager): Observable<Manager> {
    return this.http
      .post<Manager>(Configs.managerUrl + '/register', luxa, Configs.getHttpOptions())
      .pipe(
        tap((luxa: Manager) => Configs.log(`added Manager w/ id=${luxa.id}`)),
        catchError(Configs.handleError<Manager>('addManager'))
      );
  }

}
