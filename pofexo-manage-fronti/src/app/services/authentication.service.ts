import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Configs } from './configs';
import { map, tap } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {

    constructor(private http: HttpClient) { }

    login(username: string, password: string) {
        return this.http.post<any>(`${Configs.loginUrl}`, { username: username, password: password })
            .pipe(
                map(user => {
                    // login successful if there's a jwt token in the response
                    if (user && user.token) {
                        localStorage.setItem('token', JSON.stringify(user.token));
                    }
                    return user;
                }));
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
        localStorage.removeItem('currentTeam');
        localStorage.removeItem('token');
    }
}