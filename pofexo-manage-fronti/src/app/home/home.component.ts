import { Component, OnInit } from '@angular/core';
import { Manager } from '../beans/manager';
import { ManagerService } from '../services/manager.service';
import { first } from 'rxjs/operators';
import { Team } from '../beans/team';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  currentUser: Manager;
  team: Team;
  users: Manager[] = [];

  constructor(
      private userService: ManagerService,
      private route: ActivatedRoute,
      private router: Router) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.team = JSON.parse(localStorage.getItem('currentTeam'));

    if (this.team === null) {
      this.router.navigate(['/team']);
    }    
  }

  ngOnInit() {
    //this.loadAllUsers();
  }

  private loadAllUsers() {
    this.userService.getListManager(0, 5)
      .pipe(first())
      .subscribe(users => {
        this.users = users;
      });
  }

}
