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
import { Player } from '../beans/player';
var PlayerDetailComponent = /** @class */ (function () {
    function PlayerDetailComponent() {
    }
    PlayerDetailComponent.prototype.ngOnInit = function () {
    };
    __decorate([
        Input(),
        __metadata("design:type", Player)
    ], PlayerDetailComponent.prototype, "player", void 0);
    PlayerDetailComponent = __decorate([
        Component({
            selector: 'app-player-detail',
            templateUrl: './player-detail.component.html',
            styleUrls: ['./player-detail.component.css']
        }),
        __metadata("design:paramtypes", [])
    ], PlayerDetailComponent);
    return PlayerDetailComponent;
}());
export { PlayerDetailComponent };
//# sourceMappingURL=player-detail.component.js.map