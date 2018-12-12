import { Component, OnInit } from '@angular/core';

import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';

@Component({
  selector: 'wsocket',
  templateUrl: './wsocket.component.html',
  styleUrls: ['./wsocket.component.css']
})
export class WsocketComponent implements OnInit {
  messages: string[] = [];
  private stompClient = null;
  constructor() { }

  ngOnInit() {
    this.connect();
  }

  connect() {
    const socket = new SockJS('http://localhost:8080/notifications');
    console.log(socket);
    this.stompClient = Stomp.Stomp.over(socket);
    
    const _this = this;
    this.stompClient.connect('master','', function(frame){
      console.log('Connected: '+ frame);
      _this.stompClient.subscribe('/notifications/messages', function(message) {
        _this.showMessage(JSON.parse(message.body));
      });

    });
  }

  showMessage(message) {
    this.messages.push(message);
    console.log(this.messages);
  }

}
