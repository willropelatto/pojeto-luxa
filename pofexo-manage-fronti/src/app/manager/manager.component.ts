import { Component, OnInit, Input } from '@angular/core';
import { Manager } from '../beans/manager'
import { ActivatedRoute, Router } from '@angular/router';
import { ManagerService } from '../services/manager.service';
import { first } from 'rxjs/operators';
import { MatSnackBar } from '@angular/material';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.css']
})
export class ManagerComponent implements OnInit {

  @Input() luxa: Manager;
  currentUser: Manager;    
  alertService: any;

  constructor(
    private managerService: ManagerService,
    private authService: AuthenticationService,
    private route: ActivatedRoute,
    private router: Router,
    private snackBar: MatSnackBar
  ) { 
    this.currentUser = this.authService.getCurrentUser();
    //this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    console.log(this.currentUser);
    if (this.currentUser != undefined) { console.log('aeeee') } else { console.log('nops') }
  }

  getManager(): void {
    const id = +this.route.snapshot.paramMap.get('code');

    if (id > 0) {
      this.managerService.getManager(id)
        .subscribe(luxa =>
          this.luxa = luxa
        );
    } else {
      this.luxa = new Manager();
    }
  }

  newManager(tele: Manager): void {
    this.managerService.addManager(tele).pipe(first())
      .subscribe(luxa => {
        this.luxa = luxa;
        this.snackBar.open('Usuário registrado com sucesso.', 'OK', {duration: 3000});
        this.router.navigate(['/login']);        
      },
      error => {
        this.snackBar.open('Não foi possivel registrar o usuário. ' + error, '', {duration: 3000});  
      }
      );     
  }

  ngOnInit() {
    this.getManager();
  }

}
