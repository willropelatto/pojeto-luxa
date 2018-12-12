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
var PlayerService = /** @class */ (function () {
    function PlayerService(http) {
        this.http = http;
    }
    PlayerService.prototype.getPlayers = function (currentPage, pageSize) {
        var options = {
            headers: Configs.getHttpHeaders(),
            params: Configs.getHttpParams(currentPage, pageSize)
        };
        return this.http
            .get(Configs.playerUrl + '/list', options)
            .pipe(tap(function (players) { return Configs.log('fetched heroes'); }), catchError(Configs.handleError("getPlayers", [])));
    };
    PlayerService.prototype.getPlayer = function (id) {
        var options = {
            headers: Configs.getHttpHeaders()
        };
        return this.http.get(Configs.playerUrl + "/get/" + id, options)
            .pipe(tap(function (_) { return Configs.log("fetched Player id=" + id); }), catchError(Configs.handleError("getPlayer id=" + id)));
    };
    PlayerService.prototype.getPlayersName = function (name, currentPage, pageSize) {
        var options = {
            headers: Configs.getHttpHeaders(),
            params: Configs.getHttpParams(currentPage, pageSize)
        };
        return this.http
            .get(Configs.playerUrl + "/getByName/" + name, options)
            .pipe(tap(function (players) { return Configs.log("fetched Players name=" + name); }), catchError(Configs.handleError("getPlayerName name=" + name)));
    };
    PlayerService.prototype.getFilteredPlayers = function (currentPage, pageSize, filter) {
        var options = {
            headers: Configs.getHttpHeaders(),
            params: Configs.getHttpParams(currentPage, pageSize)
        };
        return this.http
            .post(Configs.playerUrl + '/getPlayers', filter, options)
            .pipe(tap(function (players) { return Configs.log('fetched heroes'); }), catchError(Configs.handleError("getPlayers", [])));
    };
    PlayerService = __decorate([
        Injectable({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [HttpClient])
    ], PlayerService);
    return PlayerService;
}());
export { PlayerService };
//# sourceMappingURL=player.service.js.map