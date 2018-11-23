import { Component, OnInit } from '@angular/core';
import { Manager } from 'src/app/beans/manager';

@Component({
  selector: 'app-manager-view',
  templateUrl: './manager-view.component.html',
  styleUrls: ['./manager-view.component.css']
})
export class ManagerViewComponent implements OnInit {

  luxa: Manager;

  constructor() {
    this.luxa = JSON.parse(localStorage.getItem('currentUser'));
   }

  ngOnInit() {
  }

}
