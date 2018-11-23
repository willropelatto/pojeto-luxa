import { Component, OnInit } from '@angular/core';
import { Manager } from '../beans/manager';
import { ManagerService } from '../services/manager.service';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  currentUser: Manager;
  users: Manager[] = [];

  constructor(private userService: ManagerService) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
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
