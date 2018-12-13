var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Component, Input } from '@angular/core';
import { TeamService } from 'src/app/services/team.service';
import { Team } from 'src/app/beans/team';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';
var TeamCreateComponent = /** @class */ (function () {
    function TeamCreateComponent(teamService, authService, snackBar, router) {
        this.teamService = teamService;
        this.authService = authService;
        this.snackBar = snackBar;
        this.router = router;
    }
    TeamCreateComponent.prototype.newTeam = function (tele) {
        var _this = this;
        this.teamService.addTeam(tele)
            .subscribe(function (team) {
            _this.team = team;
            _this.snackBar.open('Time registrado com sucesso.', 'OK', { duration: 5000 });
            _this.router.navigate(['/team/view']);
        }, function (error) {
            _this.snackBar.open('Não foi possivel registrar o time', 'OK', { duration: 5000 });
        });
    };
    TeamCreateComponent.prototype.ngOnInit = function () {
        this.user = this.authService.getCurrentUser();
        this.team = new Team();
        this.team.idUser = this.user.id;
        this.team.budget = 15000;
    };
    __decorate([
        Input(),
        __metadata("design:type", Team)
    ], TeamCreateComponent.prototype, "team", void 0);
    TeamCreateComponent = __decorate([
        Component({
            selector: 'app-team-create',
            templateUrl: './team-create.component.html',
            styleUrls: ['./team-create.component.css']
        }),
        __metadata("design:paramtypes", [TeamService,
            AuthenticationService,
            MatSnackBar,
            Router])
    ], TeamCreateComponent);
    return TeamCreateComponent;
}());
export { TeamCreateComponent };
//# sourceMappingURL=team-create.component.js.map