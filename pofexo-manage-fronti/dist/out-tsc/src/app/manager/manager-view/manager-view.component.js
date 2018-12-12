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
import { AuthenticationService } from 'src/app/services/authentication.service';
import { TeamService } from 'src/app/services/team.service';
var ManagerViewComponent = /** @class */ (function () {
    function ManagerViewComponent(authService, teamService) {
        this.authService = authService;
        this.teamService = teamService;
        this.luxa = this.authService.getCurrentUser();
        this.team = this.teamService.getCurrentTeam();
    }
    ManagerViewComponent.prototype.ngOnInit = function () {
    };
    ManagerViewComponent = __decorate([
        Component({
            selector: 'app-manager-view',
            templateUrl: './manager-view.component.html',
            styleUrls: ['./manager-view.component.css']
        }),
        __metadata("design:paramtypes", [AuthenticationService,
            TeamService])
    ], ManagerViewComponent);
    return ManagerViewComponent;
}());
export { ManagerViewComponent };
//# sourceMappingURL=manager-view.component.js.map