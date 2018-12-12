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
import { Configs } from './configs';
import { tap, catchError } from 'rxjs/operators';
var MarketService = /** @class */ (function () {
    function MarketService(http) {
        this.http = http;
    }
    MarketService.prototype.dismissPlayer = function (bid) {
        return this.http
            .post(Configs.mktUrl + '/dismiss', bid, Configs.getHttpOptions())
            .pipe(tap(function (_) { return Configs.log("player dismmissed id=" + bid.id); }), catchError(Configs.handleError('dismissPlayer')));
    };
    MarketService.prototype.placeBid = function (bid) {
        return this.http
            .post(Configs.mktUrl + '/placeBid', bid, Configs.getHttpOptions())
            .pipe(tap(function (_) { return Configs.log("bid set to player id=" + bid.id); }), catchError(Configs.handleError('placeBid')));
    };
    MarketService.prototype.openMarket = function () {
        return this.http
            .post(Configs.mktUrl + '/open', Configs.getHttpOptions())
            .pipe(tap(function (_) { return Configs.log('market is now open bitch'); }), catchError(Configs.handleError('openMarket')));
    };
    MarketService.prototype.setLeagues = function (leagues) {
        return this.http
            .post(Configs.mktUrl + '/setLeagues', leagues, Configs.getHttpOptions())
            .pipe(tap(function (_) { return Configs.log("leagues setted ids=" + leagues); }), catchError(Configs.handleError('setLeagues')));
    };
    MarketService = __decorate([
        Injectable({
            providedIn: 'root'
        }),
        __metadata("design:paramtypes", [HttpClient])
    ], MarketService);
    return MarketService;
}());
export { MarketService };
//# sourceMappingURL=market.service.js.map