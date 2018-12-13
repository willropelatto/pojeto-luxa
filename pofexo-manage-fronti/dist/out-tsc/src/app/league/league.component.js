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
import { LeagueService } from '../services/league.service';
import { MatPaginator, MatSnackBar } from '@angular/material';
import { LeagueDataSource } from './league-data-source';
import { tap } from 'rxjs/operators';
import { SelectionModel } from '@angular/cdk/collections';
import { MarketService } from '../services/market.service';
var LeagueComponent = /** @class */ (function () {
    function LeagueComponent(leagueService, mktService, snackBar) {
        this.leagueService = leagueService;
        this.mktService = mktService;
        this.snackBar = snackBar;
        this.displayedColumns = ['select', 'name', 'abbrName'];
        this.selection = new SelectionModel(true, []);
    }
    /** Whether the number of selected elements matches the total number of rows. */
    LeagueComponent.prototype.isAllSelected = function () {
        var numSelected = this.selection.selected.length;
        var numRows = this.dataSource.data.length;
        return numSelected === numRows;
    };
    /** Selects all rows if they are not all selected; otherwise clear selection. */
    LeagueComponent.prototype.masterToggle = function () {
        var _this = this;
        this.isAllSelected() ?
            this.selection.clear() :
            this.dataSource.data.forEach(function (row) { return _this.selection.select(row); });
    };
    LeagueComponent.prototype.ngOnInit = function () {
        this.dataSource = new LeagueDataSource(this.leagueService);
        this.dataSource.loadLeagues('ASC', 0, 5);
    };
    LeagueComponent.prototype.ngAfterViewInit = function () {
        var _this = this;
        this.paginator.page
            .pipe(tap(function () {
            _this.loadLeaguesPage();
            _this.selection.clear();
        }))
            .subscribe();
    };
    LeagueComponent.prototype.loadLeaguesPage = function () {
        this.dataSource.loadLeagues('asc', this.paginator.pageIndex, this.paginator.pageSize);
    };
    LeagueComponent.prototype.defineLeagues = function () {
        var _this = this;
        var lgs = ([]);
        this.dataSource.data.forEach(function (row) { return _this.selection.isSelected(row) ? lgs.push(row['id']) : ''; });
        if (lgs.length > 0) {
            this.mktService.setLeagues(lgs)
                .subscribe(function () {
                _this.snackBar.open('Ligas definidas!', '', { duration: 5000 });
            }, function (err) {
                _this.snackBar.open('Erro ao definir as ligas!', '', { duration: 5000 });
            });
        }
    };
    __decorate([
        ViewChild(MatPaginator),
        __metadata("design:type", MatPaginator)
    ], LeagueComponent.prototype, "paginator", void 0);
    LeagueComponent = __decorate([
        Component({
            selector: 'app-league',
            templateUrl: './league.component.html',
            styleUrls: ['./league.component.css']
        }),
        __metadata("design:paramtypes", [LeagueService,
            MarketService,
            MatSnackBar])
    ], LeagueComponent);
    return LeagueComponent;
}());
export { LeagueComponent };
//# sourceMappingURL=league.component.js.map