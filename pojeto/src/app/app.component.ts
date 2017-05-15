import { UserService } from './user/shared/user.service';
import { User } from './user/shared/user.model';
import { Component } from '@angular/core';

@Component({
  selector: 'pojeto-app',
  templateUrl: 'app.component.html',
})
export class AppComponent {  
 
    currentUser: User;
    users: User[] = [];
 
    constructor(private userService: UserService) {
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    }
 
    ngOnInit() {
    }
 


}
 