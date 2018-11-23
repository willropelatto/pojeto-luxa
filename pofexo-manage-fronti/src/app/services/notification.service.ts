import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Configs } from './configs';
import { tap, catchError } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private http: HttpClient) { }

  getNotificationTeam(team: number, currentPage: any, pageSize: any) {
    let options = {
      headers: Configs.getHttpHeaders(),
      params: Configs.getHttpParams(currentPage, pageSize)
    };

    return this.http
      .get<Notification[]>(`${Configs.notificationUrl}/getByTeam/${team}`, options)
      .pipe(
        tap(ntf => Configs.log('fetched notification')),
        catchError(Configs.handleError("getNotificationTeam", []))
      );
  }  

  getLastNotification(team: number, currentPage: any, pageSize: any) {
    let options = {
      headers: Configs.getHttpHeaders(),
      params: Configs.getHttpParams(currentPage, pageSize)
    };

    return this.http
      .get<Notification[]>(`${Configs.notificationUrl}/getLasts/${team}`, options)
      .pipe(
        tap(ntf => Configs.log('fetched notification')),
        catchError(Configs.handleError("getLastNotification", []))
      );
  }  
  
  markAsRead(id: number): Observable<any> {
    return this.http
      .post(Configs.notificationUrl + '/markAsRead', id, Configs.getHttpOptions())
      .pipe(
        tap(_ => Configs.log(`marked notification id=${id}`)),
        catchError(Configs.handleError<any>('markAsRead'))
      );
  }  

  markAllAsRead(team: number): Observable<any> {
    return this.http
      .post(Configs.notificationUrl + '/markAllAsRead', team, Configs.getHttpOptions())
      .pipe(
        tap(_ => Configs.log(`marked all notification of team=${team}`)),
        catchError(Configs.handleError<any>('markAllAsRead'))
      );
  }    
}
