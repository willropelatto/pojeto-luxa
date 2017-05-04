import { UserService } from './../shared/user.service';
import { AlertService } from './../../util/alert.service';
import { Component } from '@angular/core';
import { Router } from '@angular/router'; 

@Component({
    templateUrl: 'user-cadastrar.component.html'
})
 
export class RegisterComponent {
    model: any = {};
    loading = false;
 
    constructor(
        private router: Router,
        private userService: UserService,
        private alertService: AlertService) { }
 
    register() {
        this.loading = true;
        this.userService.create(this.model)
            .subscribe(
                data => {
                    // set success message and pass true paramater to persist the message after redirecting to the login page
                    this.alertService.success('Registro Efetuado com sucesso', true);
                    this.router.navigate(['/login']);
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });
    }
}