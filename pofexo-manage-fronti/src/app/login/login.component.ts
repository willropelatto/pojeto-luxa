import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';
import { AlertService } from '../services/alert.service';
import { first } from 'rxjs/operators';
import { MatSnackBar } from '@angular/material';
import { ManagerService } from '../services/manager.service';
import { TeamService } from '../services/team.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    loginForm: FormGroup;
    loading = false;
    submitted = false;
    returnUrl: string;
    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthenticationService,
        private snackBar: MatSnackBar,
        private managerService: ManagerService,
        private teamService: TeamService
    ) { }

    ngOnInit() {
        this.loginForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required]
        });

        this.authenticationService.logout();
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    }

    // convenience getter for easy access to form fields
    get f() { return this.loginForm.controls; }

    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
        if (this.loginForm.invalid) {
            return;
        }

        this.loading = true;
        this.authenticationService.login(this.f.username.value, this.f.password.value)
            .pipe(first())
            .subscribe(
                data => {
                    this.loadEnviromentUser(this.f.username.value);
                },
                error => {
                    this.snackBar.open("Usuário/Senha inválidos.", "ok", { duration: 5000 });
                    this.loading = false;
                });
    }

    private loadEnviromentUser(username) {
        this.managerService.getManagerName(username)
            .subscribe(
                usr => {
                    this.teamService.getTeamUser(usr.id)
                        .subscribe(
                            tm => localStorage.setItem('currentTeam', JSON.stringify(tm.id))
                        );

                    localStorage.setItem('currentUser', JSON.stringify(usr));
                    this.router.navigate([this.returnUrl]);
                }
            );
    }

}
