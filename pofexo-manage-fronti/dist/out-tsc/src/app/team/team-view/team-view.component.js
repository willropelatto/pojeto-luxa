var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Component, ViewChild, Input } from '@angular/core';
import { Team } from 'src/app/beans/team';
import { TeamService } from 'src/app/services/team.service';
import { ActivatedRoute } from '@angular/router';
import { MatPaginator, MatTableDataSource } from '@angular/material';
var TeamViewComponent = /** @class */ (function () {
    function TeamViewComponent(teamService, route) {
        this.teamService = teamService;
        this.route = route;
        this.displayedColumns = ['position', 'name', 'rating', 'status'];
    }
    TeamViewComponent.prototype.getTeam = function () {
        var _this = this;
        this.team = this.teamService.getCurrentTeam();
        var id = this.team.id;
        this.teamService.getTeamId(id)
            .subscribe(function (team) {
            _this.team = team;
            _this.dataSource = new MatTableDataSource(_this.team.players);
            setTimeout(function () { return _this.dataSource.paginator = _this.paginator; }); //Isso é loucura, só funciona o paginator se colocar esse comando de timout
        });
    };
    TeamViewComponent.prototype.ngOnInit = function () {
        this.getTeam();
    };
    __decorate([
        Input(),
        __metadata("design:type", Team)
    ], TeamViewComponent.prototype, "team", void 0);
    __decorate([
        ViewChild(MatPaginator),
        __metadata("design:type", MatPaginator)
    ], TeamViewComponent.prototype, "paginator", void 0);
    TeamViewComponent = __decorate([
        Component({
            selector: 'app-team-view',
            templateUrl: './team-view.component.html',
            styleUrls: ['./team-view.component.css']
        }),
        __metadata("design:paramtypes", [TeamService,
            ActivatedRoute])
    ], TeamViewComponent);
    return TeamViewComponent;
}());
export { TeamViewComponent };
//# sourceMappingURL=team-view.component.js.map