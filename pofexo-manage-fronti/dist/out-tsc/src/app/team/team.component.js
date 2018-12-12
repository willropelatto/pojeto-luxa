var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Component } from '@angular/core';
import { TeamService } from '../services/team.service';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';
var TeamComponent = /** @class */ (function () {
    function TeamComponent(teamService, authService, router) {
        this.teamService = teamService;
        this.authService = authService;
        this.router = router;
        this.teamId = 0;
    }
    TeamComponent.prototype.ngOnInit = function () {
        this.team = this.teamService.getCurrentTeam();
        this.user = this.authService.getCurrentUser();
        if (this.team !== null) {
            this.redirectToView();
        }
        else {
            this.getTeam();
        }
    };
    TeamComponent.prototype.redirectToView = function () {
        this.router.navigate(['/team/view']);
    };
    TeamComponent.prototype.getTeam = function () {
        var _this = this;
        this.teamService.getTeamUser(this.user.id)
            .subscribe(function (tm) {
            if (tm) {
                _this.teamId = tm.id;
                localStorage.setItem('currentTeam', JSON.stringify(tm));
                _this.redirectToView();
            }
            else {
                _this.router.navigate(['/team/new']);
            }
        });
    };
    TeamComponent = __decorate([
        Component({
            selector: 'app-team',
            templateUrl: './team.component.html',
            styleUrls: ['./team.component.css']
        }),
        __metadata("design:paramtypes", [TeamService,
            AuthenticationService,
            Router])
    ], TeamComponent);
    return TeamComponent;
}());
export { TeamComponent };
//# sourceMappingURL=team.component.js.map