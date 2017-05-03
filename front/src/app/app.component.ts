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
  <router-outlet></router-outlet>
  `,
})
export class AppComponent {
}
