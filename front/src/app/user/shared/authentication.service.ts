import { User } from './user.model';
import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map'


@Injectable()
export class AuthenticationService {

	private path = 'http://localhost:8585/pofexo/rest/user/login';
    
	constructor(private http: Http) { }

    /*    create(user: User) {
        return this.http.post(this.API_URL + '/cadastrar/', user, this.jwt()).map((response: Response) => response.json());
    }*/
 
    login(user : User) {
        console.log('login funcou');
        return 'tssad';
       /* return this.http.post(this.path, user) 
            .map((response: Response) => {
                // login successful if there's a jwt token in the response
                let user = response.json();
                if (user && user.token) {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                   
                    localStorage.setItem('currentUser', JSON.stringify(user));
                }
            });*/
    }
 
    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
    }
}