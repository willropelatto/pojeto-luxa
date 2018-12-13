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
import { PlayerFilter } from '../beans/player-filter';
import { PlayerService } from '../services/player.service';
var PlayersComponent = /** @class */ (function () {
    function PlayersComponent(playerService) {
        this.playerService = playerService;
        this.size = 5;
        this.page = 0;
    }
    PlayersComponent.prototype.onSelect = function (player) {
        this.selectedPlayer = player;
    };
    PlayersComponent.prototype.getPlayers = function () {
        var _this = this;
        var xuxa = new PlayerFilter();
        xuxa.name = 'ronaldo';
        this.playerService.getFilteredPlayers(0, 5, xuxa).subscribe(function (juca) { return _this.players = juca['content']; });
        //    this.playerService.getPlayers(0,10).subscribe(juca => 
        //      this.players = juca['content']
        //    );    
    };
    PlayersComponent.prototype.getPlayer = function (id) {
        this.playerService.getPlayer(id).subscribe();
    };
    /*
    carregarLivros() {
    this._livroService.carregarLivros(this.currentPage, this.pageSize)
    .subscribe(data => {
    this.filteredData = data['content'];
    this.data = data['content'];
    this.filteredTotal = data['totalElements'];
    });
    }
    
    */
    PlayersComponent.prototype.ngOnInit = function () {
        this.getPlayers();
    };
    PlayersComponent = __decorate([
        Component({
            selector: 'app-players',
            templateUrl: './players.component.html',
            styleUrls: ['./players.component.css']
        }),
        __metadata("design:paramtypes", [PlayerService])
    ], PlayersComponent);
    return PlayersComponent;
}());
export { PlayersComponent };
//# sourceMappingURL=players.component.js.map