import { Injectable, Output, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Configs } from './configs';
import { map, tap } from 'rxjs/operators';
import { Manager } from '../beans/manager';

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {

    @Output() sucessfull: EventEmitter<boolean> = new EventEmitter();

    constructor(
        private http: HttpClient
    ) { }

    login(username: string, password: string) {        
        return this.http.post<any>(`${Configs.loginUrl}`, { username: username, password: password })
            .pipe(
                map(user => { 
                    // login successful if there's a jwt token in the response
                    if (user && user.token) {                        
                        localStorage.setItem('token', JSON.stringify(user.token));                        
                        this.sucessfull.emit(true);
                    }
                    
                    return user;
                }));
    }


    getCurrentUser(): Manager {        
        let manager: Manager;
        manager = JSON.parse(localStorage.getItem('currentUser'));
        if (manager !== null) {
            this.sucessfull.emit(true);
            return manager;
        } else {
            this.logout();
        }
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
        localStorage.removeItem('currentTeam');
        localStorage.removeItem('token');
        this.sucessfull.emit(false);
    }
}
