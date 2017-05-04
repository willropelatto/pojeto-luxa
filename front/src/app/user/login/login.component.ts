import { User } from './../shared/user.model';
import { AuthenticationService } from './../shared/authentication.service';
import { AlertService } from './../../util/alert.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
    templateUrl: 'login.component.html'
})
 
export class LoginComponent implements OnInit {
    model: User;
    loading = false;
    returnUrl: string;
 
    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthenticationService,
        private alertService: AlertService) { }
 
    ngOnInit() {
        // reset login status
        this.authenticationService.logout();
 
        // get return url from route parameters or default to '/'
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    }
 
    login() {
        this.loading = true;
        this.authenticationService.login(this.model)
            .subscribe(
                data => {                   
                    this.alertService.success('Usuário Logado com Sucesso');
                    //this.router.navigate([this.returnUrl]);
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });
    }
}