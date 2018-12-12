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
import { ManagerService } from '../services/manager.service';
import { first } from 'rxjs/operators';
import { Router } from '@angular/router';
import { TeamService } from '../services/team.service';
import { AuthenticationService } from '../services/authentication.service';
var HomeComponent = /** @class */ (function () {
    function HomeComponent(userService, teamService, authService, router) {
        this.userService = userService;
        this.teamService = teamService;
        this.authService = authService;
        this.router = router;
        this.users = [];
        this.currentUser = this.authService.getCurrentUser();
        this.team = this.teamService.getCurrentTeam();
        if (this.team === null) {
            this.router.navigate(['/team']);
        }
    }
    HomeComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.teamService.getTeamId(this.team.id)
            .subscribe(function (team) {
            _this.team = team;
        });
    };
    HomeComponent.prototype.loadAllUsers = function () {
        var _this = this;
        this.userService.getListManager(0, 5)
            .pipe(first())
            .subscribe(function (users) {
            _this.users = users;
        });
    };
    HomeComponent = __decorate([
        Component({
            selector: 'app-home',
            templateUrl: './home.component.html',
            styleUrls: ['./home.component.css']
        }),
        __metadata("design:paramtypes", [ManagerService,
            TeamService,
            AuthenticationService,
            Router])
    ], HomeComponent);
    return HomeComponent;
}());
export { HomeComponent };
//# sourceMappingURL=home.component.js.map