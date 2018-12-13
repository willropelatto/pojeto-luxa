var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Component, ViewChild } from '@angular/core';
import { MarketService } from '../services/market.service';
import { PlayerService } from '../services/player.service';
import { MatPaginator, MatSnackBar } from '@angular/material';
import { tap } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';
import { TeamService } from '../services/team.service';
var MarketComponent = /** @class */ (function () {
    function MarketComponent(marketService, playerService, teamService, snackBar, route, router) {
        var _this = this;
        this.marketService = marketService;
        this.playerService = playerService;
        this.teamService = teamService;
        this.snackBar = snackBar;
        this.route = route;
        this.router = router;
        this.totalPlayer = 0;
        this.route.queryParams.subscribe(function (params) {
            _this.playerFilter = params["playerFilter"];
        });
        this.team = this.teamService.getCurrentTeam();
        if (!(this.team.id > 0)) {
            this.router.navigate(['/team']);
        }
    }
    MarketComponent.prototype.placeBid = function (player) {
        var _this = this;
        player.bid.team = this.team.id;
        player.bid.bidValue = player.bid.nextValue;
        console.log(player);
        this.marketService.placeBid(player).
            subscribe(function (pl) {
            player = pl;
            if (player.bid.status == "NO_MONEY") {
                _this.snackBar.open('Dinheiro insuficiente.', 'OK', { duration: 5000 });
            }
            else if (player.bid.status == "RESYNC") {
                _this.snackBar.open('Lance superado.', 'OK', { duration: 5000 });
            }
            else if (player.bid.status == "MARKET_CLOSE") {
                _this.snackBar.open('Mercado está fechado.', 'OK', { duration: 5000 });
            }
            else {
                _this.snackBar.open('Lance efetuado pelo jogador ' + pl.name + '.', 'OK', { duration: 5000 });
            }
            //chamada só pra atualizar budget    
            _this.teamService.getTeamId(_this.team.id).subscribe();
            //todo: refresh na página?, só queria atualizar esse registro...
        }, function (error) {
            _this.snackBar.open('Não foi possivel dar o lance no jogador.', 'OK', { duration: 5000 });
        });
    };
    MarketComponent.prototype.ngOnInit = function () {
        this.getPlayers(0, 5);
    };
    MarketComponent.prototype.ngAfterViewInit = function () {
        var _this = this;
        this.paginator.page
            .pipe(tap(function () { return _this.loadPlayersPage(); }))
            .subscribe();
    };
    MarketComponent.prototype.loadPlayersPage = function () {
        this.getPlayers(this.paginator.pageIndex, this.paginator.pageSize);
    };
    MarketComponent.prototype.getPlayers = function (pageIndex, pageSize) {
        var _this = this;
        var promise = this.playerService.getFilteredPlayers(pageIndex, pageSize, this.playerFilter).toPromise();
        promise.then(function (juca) {
            _this.players = juca['content'];
            _this.totalPlayer = juca['totalElements'];
        });
    };
    MarketComponent.prototype.receiveBid = function (player) {
        this.placeBid(player);
    };
    __decorate([
        ViewChild(MatPaginator),
        __metadata("design:type", MatPaginator)
    ], MarketComponent.prototype, "paginator", void 0);
    MarketComponent = __decorate([
        Component({
            selector: 'app-market',
            templateUrl: './market.component.html',
            styleUrls: ['./market.component.css']
        }),
        __metadata("design:paramtypes", [MarketService,
            PlayerService,
            TeamService,
            MatSnackBar,
            ActivatedRoute,
            Router])
    ], MarketComponent);
    return MarketComponent;
}());
export { MarketComponent };
//# sourceMappingURL=market.component.js.map