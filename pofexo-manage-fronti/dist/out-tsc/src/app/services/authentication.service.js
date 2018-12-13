var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Injectable, Output, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Configs } from './configs';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';
var AuthenticationService = /** @class */ (function () {
    function AuthenticationService(http, router) {
        this.http = http;
        this.router = router;
        this.sucessfull = new EventEmitter();
    }
    AuthenticationService.prototype.login = function (username, password) {
        var _this = this;
        return this.http.post("" + Configs.loginUrl, { username: username, password: password })
            .pipe(map(function (user) {
            _this.sucessfull.emit(true);
            // login successful if there's a jwt token in the response
            if (user && user.token) {
                localStorage.setItem('token', JSON.stringify(user.token));
            }
            return user;
        }));
    };
    AuthenticationService.prototype.getCurrentUser = function () {
        var manager;
        manager = JSON.parse(localStorage.getItem('currentUser'));
        if (manager !== null) {
            this.sucessfull.emit(true);
            return manager;
        }
        else {
            this.logout();
            this.router.navigate(['/login']);
        }
    };
    AuthenticationService.prototype.logout = function () {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
        localStorage.removeItem('currentTeam');
        localStorage.removeItem('token');
        this.sucessfull.emit(false);
    };
    __decorate([
        Output(),
        __metadata("design:type", EventEmitter)
    ], AuthenticationService.prototype, "sucessfull", void 0);
    AuthenticationService = __decorate([
        Injectable({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [HttpClient,
            Router])
    ], AuthenticationService);
    return AuthenticationService;
}());
export { AuthenticationService };
//# sourceMappingURL=authentication.service.js.map