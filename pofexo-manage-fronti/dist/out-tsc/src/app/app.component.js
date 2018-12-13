var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { MediaMatcher } from '@angular/cdk/layout';
import { ChangeDetectorRef, Component } from '@angular/core';
import { AuthenticationService } from './services/authentication.service';
import { TeamService } from './services/team.service';
var AppComponent = /** @class */ (function () {
    function AppComponent(authService, teamService, changeDetectorRef, media) {
        this.authService = authService;
        this.teamService = teamService;
        this.logado = false;
        this.budget = 0;
        this.mobileQuery = media.matchMedia('(max-width: 600px)');
        this._mobileQueryListener = function () { return changeDetectorRef.detectChanges(); };
        this.mobileQuery.addListener(this._mobileQueryListener);
    }
    AppComponent.prototype.ngOnDestroy = function () {
        this.mobileQuery.removeListener(this._mobileQueryListener);
    };
    AppComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.authService.sucessfull.subscribe(function (item) { return _this.logado = item; });
        this.teamService.budgetEvent.subscribe(function (value) { return _this.budget = value; });
    };
    AppComponent.prototype.getLogado = function () {
        return this.logado;
    };
    AppComponent.prototype.getBudget = function () {
        return this.budget;
    };
    AppComponent = __decorate([
        Component({
            selector: 'app-root',
            templateUrl: './app.component.html',
            styleUrls: ['./app.component.css']
        }),
        __metadata("design:paramtypes", [AuthenticationService, TeamService,
            ChangeDetectorRef, MediaMatcher])
    ], AppComponent);
    return AppComponent;
}());
export { AppComponent };
//# sourceMappingURL=app.component.js.map