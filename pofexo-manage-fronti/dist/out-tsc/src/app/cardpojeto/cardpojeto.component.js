var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Player } from '../beans/player';
var CardpojetoComponent = /** @class */ (function () {
    function CardpojetoComponent() {
        this.informaBid = new EventEmitter();
    }
    CardpojetoComponent.prototype.ngOnInit = function () {
    };
    CardpojetoComponent.prototype.feedback = function (player) {
        this.informaBid.emit(player);
    };
    CardpojetoComponent.prototype.getHeader = function () {
        return "header" + this.player.id.toString();
    };
    CardpojetoComponent.prototype.getCollapse = function () {
        return "collapse" + this.player.id.toString();
    };
    CardpojetoComponent.prototype.getCollapseId = function () {
        return "#collapse" + this.player.id.toString();
    };
    CardpojetoComponent.prototype.chamadaRest = function () {
        console.log('Chamada rest do botao DAR LANCE.');
    };
    __decorate([
        Output(),
        __metadata("design:type", Object)
    ], CardpojetoComponent.prototype, "informaBid", void 0);
    __decorate([
        Input(),
        __metadata("design:type", Player)
    ], CardpojetoComponent.prototype, "player", void 0);
    CardpojetoComponent = __decorate([
        Component({
            selector: 'cardpojeto',
            templateUrl: './cardpojeto.component.html',
            styleUrls: ['./cardpojeto.component.css']
        }),
        __metadata("design:paramtypes", [])
    ], CardpojetoComponent);
    return CardpojetoComponent;
}());
export { CardpojetoComponent };
//# sourceMappingURL=cardpojeto.component.js.map