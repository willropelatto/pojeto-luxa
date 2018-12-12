var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap, catchError } from 'rxjs/operators';
import { Configs } from './configs';
var ManagerService = /** @class */ (function () {
    function ManagerService(http) {
        this.http = http;
    }
    ManagerService.prototype.getManagerName = function (username) {
        var options = {
            headers: Configs.getHttpHeaders()
        };
        return this.http
            .get(Configs.managerUrl + "/getUser/" + username, options)
            .pipe(tap(function (_) { return Configs.log("fetched manager name=" + username); }), catchError(Configs.handleError("getManagerName name=" + username)));
    };
    ManagerService.prototype.getListManager = function (currentPage, pageSize) {
        var options = {
            headers: Configs.getHttpHeaders(),
            params: Configs.getHttpParams(currentPage, pageSize)
        };
        return this.http
            .get(Configs.managerUrl + '/list', options)
            .pipe(tap(function (players) { return Configs.log('fetched Manager'); }), catchError(Configs.handleError("getListManager", [])));
    };
    ManagerService.prototype.getManager = function (code) {
        var options = {
            headers: Configs.getHttpHeaders()
        };
        return this.http
            .get(Configs.managerUrl + "/get/" + code, options)
            .pipe(tap(function (_) { return Configs.log("fetched manager id=" + code); }), catchError(Configs.handleError("getManager id=" + code)));
    };
    ManagerService.prototype.updateManager = function (luxa) {
        return this.http
            .post(Configs.managerUrl + '/update', luxa, Configs.getHttpOptions())
            .pipe(tap(function (_) { return Configs.log("updated luxa id=" + luxa.id); }), catchError(Configs.handleError('updateManager')));
    };
    ManagerService.prototype.addManager = function (luxa) {
        return this.http
            .post(Configs.managerUrl + '/register', luxa, Configs.getHttpOptions())
            .pipe(tap(function (luxa) { return Configs.log("added Manager w/ id=" + luxa.id); }), catchError(Configs.handleError('addManager')));
    };
    ManagerService = __decorate([
        Injectable({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [HttpClient])
    ], ManagerService);
    return ManagerService;
}());
export { ManagerService };
//# sourceMappingURL=manager.service.js.map