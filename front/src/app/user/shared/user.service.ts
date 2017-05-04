import { User } from './user.model';
import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
 
 
@Injectable()
export class UserService {
    constructor(private http: Http) { }

	private API_URL: string = 'http://localhost:8585/pofexo/rest/user';
 
    getAll() {
        return this.http.get(this.API_URL + '/todosUsuarios', this.jwt()).map((response: Response) => response.json());
    }
 
    getById(id: number) {
        return this.http.get(this.API_URL + '/getUsuario/' + id, this.jwt()).map((response: Response) => response.json());
    }
 
    create(user: User) {
        return this.http.post(this.API_URL + '/cadastrar/', user, this.jwt()).map((response: Response) => response.json());
    }
 
    update(user: User) {
        return this.http.put(this.API_URL + '/alterar/' + user.id, user, this.jwt()).map((response: Response) => response.json());
    }
 
    delete(id: number) {
        return this.http.delete(this.API_URL + '/excluir/' + id, this.jwt()).map((response: Response) => response.json());
    }
 
    // private helper methods
 
    private jwt() {
        // create authorization header with jwt token
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (currentUser && currentUser.token) {
            let headers = new Headers({ 'Authorization': 'Bearer ' + currentUser.token });
            return new RequestOptions({ headers: headers });
        }
    }
}