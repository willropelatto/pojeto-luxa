import { UserService } from './user/shared/user.service';
import { User } from './user/shared/user.model';
import { Component } from '@angular/core';

@Component({
  selector: 'pojeto-app',
  template: `
	<nav class="navbar navbar-inverse">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="#">Pojeto</a>
      </div>
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
          <li><a routerLink="/">Inicial</a></li>
          <li><a routerLink="/players">Jogadores</a></li>
          <li><a routerLink="/leagues">Ligas</a></li>
          <li><a routerLink="/teams">Times</a></li>
          <li><a routerLink="/transfermarkets">Mercado</a></li>
          <li><a routerLink="/bidinfos">Lances</a></li>          
        </ul>
      </div>
    </div>
   </nav>
   
  <alert></alert>
  <router-outlet></router-outlet>
  `,
})
export class AppComponent {  
 /*
    currentUser: User;
    users: User[] = [];
 
    constructor(private userService: UserService) {
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    }
 
    ngOnInit() {
        this.loadAllUsers();
    }
 
    deleteUser(id: number) {
        this.userService.delete(id).subscribe(() => { this.loadAllUsers() });
    }
 
    private loadAllUsers() {
        this.userService.getAll().subscribe(users => { this.users = users; });

        <div class="col-md-6 col-md-offset-3">
    <h1>Hi {{currentUser.nome}}!</h1>
    <p>You're logged in with Angular 2!!</p>
    <h3>All registered users:</h3>
    <ul>
        <li *ngFor="let user of users">
            {{user.login}} ({{user.nome}} {{user.sobrenome}})
            - <a (click)="deleteUser(user.id)">Delete</a>
        </li>
    </ul>
    <p><a [routerLink]="['/login']">Logout</a></p>
    </div>  
    }*/
}
