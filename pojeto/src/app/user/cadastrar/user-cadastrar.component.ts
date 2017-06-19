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
        public router: Router,
        public userService: UserService,
        public alertService: AlertService) { }
 
    register() {
        this.loading = true;
        this.userService.create(this.model)
            .subscribe(
                (res) => {
                    this.alertService.success('Registro Efetuado com sucesso', true);
                    this.router.navigate(['/login']);
                },
                (err) => {
                    this.alertService.error(err);
                    this.loading = false;
                });
    }

}