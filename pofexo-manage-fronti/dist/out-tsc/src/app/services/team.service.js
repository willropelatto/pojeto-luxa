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
import { tap, catchError } from 'rxjs/operators';
var TeamService = /** @class */ (function () {
    function TeamService(http) {
        this.http = http;
        this.budgetEvent = new EventEmitter();
    }
    TeamService.prototype.getTeamList = function (currentPage, pageSize) {
        var options = {
            headers: Configs.getHttpHeaders(),
            params: Configs.getHttpParams(currentPage, pageSize)
        };
        return this.http
            .get(Configs.teamUrl + '/list', options)
            .pipe(tap(function (players) { return Configs.log('fetched Team'); }), catchError(Configs.handleError("getTeamList", [])));
    };
    TeamService.prototype.getTeamId = function (id) {
        var _this = this;
        var options = {
            headers: Configs.getHttpHeaders()
        };
        return this.http
            .get(Configs.teamUrl + "/getTeam/" + id, options)
            .pipe(tap(function (tm) {
            _this.budgetEvent.emit(tm.budget);
            Configs.log("fetched Team id=" + id);
        }), catchError(Configs.handleError("getTeam id=" + id)));
    };
    TeamService.prototype.getTeamUser = function (user) {
        var _this = this;
        var options = {
            headers: Configs.getHttpHeaders()
        };
        return this.http
            .get(Configs.teamUrl + "/getByUser/" + user, options)
            .pipe(tap(function (tm) {
            _this.budgetEvent.emit(tm.budget);
            Configs.log("fetched user id=" + user);
        }), catchError(Configs.handleError("getTeam id=" + user)));
    };
    TeamService.prototype.getTeamPlayers = function (id) {
        var options = {
            headers: Configs.getHttpHeaders()
        };
        return this.http
            .get(Configs.teamUrl + "/getTeam/" + id + "/contract", options)
            .pipe(tap(function (_) { return Configs.log("fetched Team id=" + id); }), catchError(Configs.handleError("getTeam id=" + id)));
    };
    TeamService.prototype.getTeamPlayersBid = function (id) {
        var options = {
            headers: Configs.getHttpHeaders()
        };
        return this.http
            .get(Configs.teamUrl + "/getTeam/" + id + "/onbid", options)
            .pipe(tap(function (_) { return Configs.log("fetched Team id=" + id); }), catchError(Configs.handleError("getTeam id=" + id)));
    };
    TeamService.prototype.addTeam = function (tm) {
        return this.http
            .post(Configs.teamUrl + '/register', tm, Configs.getHttpOptions())
            .pipe(tap(function (tm) { return Configs.log("added Team w/ id=" + tm.id); }), catchError(Configs.handleError('addTeam')));
    };
    TeamService.prototype.getCurrentTeam = function () {
        var team;
        team = JSON.parse(localStorage.getItem('currentTeam'));
        this.budgetEvent.emit(team.budget);
        return team;
    };
    __decorate([
        Output(),
        __metadata("design:type", EventEmitter)
    ], TeamService.prototype, "budgetEvent", void 0);
    TeamService = __decorate([
        Injectable({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [HttpClient])
    ], TeamService);
    return TeamService;
}());
export { TeamService };
//# sourceMappingURL=team.service.js.map