import { HttpUtilService } from './../../util/http-util-service';
import { User } from './user.model';
import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map'


@Injectable()
export class AuthenticationService {

	private path = 'http://localhost:8585/pofexo/rest/user/login';

    private loginUrl:string = 'user/login';
	private logoutUrl:string = '';
    
	constructor(private http: Http, private httpUtil :HttpUtilService) { }

  
    login(user : User) {
        return this.http.put(this.httpUtil.url(this.loginUrl), user, 
                        this.httpUtil.headers())
                    .map(this.httpUtil.extrairDadosUser)
                    .catch(this.httpUtil.processarErros);                    
    }
 
    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
    }

    isLogged(){
        return localStorage['token'];
    }
}