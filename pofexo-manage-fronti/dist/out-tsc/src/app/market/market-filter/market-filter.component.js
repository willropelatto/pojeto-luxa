var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Component, Input } from '@angular/core';
import { PlayerService } from 'src/app/services/player.service';
import { PlayerFilter } from 'src/app/beans/player-filter';
import { POSITIONS } from 'src/app/beans/misc';
import { Router, ActivatedRoute } from '@angular/router';
import { TeamService } from 'src/app/services/team.service';
var MarketFilterComponent = /** @class */ (function () {
    function MarketFilterComponent(playerService, teamService, router, route) {
        this.playerService = playerService;
        this.teamService = teamService;
        this.router = router;
        this.route = route;
        this.filter = new PlayerFilter();
        this.positions = POSITIONS;
        this.team = this.teamService.getCurrentTeam();
    }
    MarketFilterComponent.prototype.ngOnInit = function () {
    };
    MarketFilterComponent.prototype.filterExtra = function (filterPlayer) {
        var navextras = {
            queryParams: { "playerFilter": JSON.stringify(filterPlayer) }
        };
        return navextras;
    };
    MarketFilterComponent.prototype.doFilterPlayer = function () {
        this.router.navigate(['/market/panel'], this.filterExtra(this.filter));
    };
    MarketFilterComponent.prototype.getPlayers = function () {
        var _this = this;
        this.playerService.getFilteredPlayers(0, 5, this.filter).subscribe(function (juca) { return _this.players = juca['content']; });
    };
    __decorate([
        Input(),
        __metadata("design:type", Object)
    ], MarketFilterComponent.prototype, "filter", void 0);
    MarketFilterComponent = __decorate([
        Component({
            selector: 'app-market-filter',
            templateUrl: './market-filter.component.html',
            styleUrls: ['./market-filter.component.css']
        }),
        __metadata("design:paramtypes", [PlayerService,
            TeamService,
            Router,
            ActivatedRoute])
    ], MarketFilterComponent);
    return MarketFilterComponent;
}());
export { MarketFilterComponent };
//# sourceMappingURL=market-filter.component.js.map