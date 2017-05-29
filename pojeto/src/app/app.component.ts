import { TeamService } from './team/shared/team.service';
import { Team } from './team/shared/team.model';
import { UserService } from './user/shared/user.service';
import { User } from './user/shared/user.model';
import { Component } from '@angular/core';

@Component({
  selector: 'pojeto-app',
  styleUrls: ['app.component.css'],
  templateUrl: 'app.component.html',
})
export class AppComponent {  
 
    currentUser: User;
    users: User[] = [];
    team : Team;
    private msgErro : string;
 
    constructor(private userService: UserService, private teamService : TeamService) {
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    }
 
    ngOnInit() {
        if (this.currentUser){
            this.teamService.buscarPorIdUser(this.currentUser.id)
                .subscribe((team : any[]) => this.team = team,                
                error => this.msgErro = error);                
        }
    }
 


}
 