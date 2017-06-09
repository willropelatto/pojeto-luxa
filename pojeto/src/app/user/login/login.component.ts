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

    private usuario: string;
    private senha: string;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthenticationService,
        private alertService: AlertService) { }

    ngOnInit() {
        this.authenticationService.logout();

        // get return url from route parameters or default to '/'
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    }

    login() {
        this.authenticationService.logout();
        let user = new User();
        user.login = this.usuario;
        user.senha = this.senha;

        this.authenticationService.login(user)
            .subscribe(
            user => this.processarLogin(user),
            error => this.alertService.error(error));
    }


    processarLogin(user: User) {
        this.alertService.success('Usuário Logado com Sucesso');
        this.router.navigate(['/teams/cadastrar']);
    }

}